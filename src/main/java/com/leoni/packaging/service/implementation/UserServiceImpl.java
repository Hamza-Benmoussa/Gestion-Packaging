package com.leoni.packaging.service.implementation;

import com.leoni.packaging.dao.UserRepository;
import com.leoni.packaging.dto.ChangePasswordForm;
import com.leoni.packaging.enums.UserStatus;
import com.leoni.packaging.model.AppUser;
import com.leoni.packaging.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()-> new NoSuchElementException("User Not Found"));
    }

    @Override
    public AppUser findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new NoSuchElementException("User Not Found"));
    }

    @Override
    public Page<AppUser> findUser(String search, int page, int size) {
        return userRepository.findAppUser(search, PageRequest.of(page, size));
    }

    @Override
    public AppUser addUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getLogin()));
        user.setStatus(UserStatus.initial);
        return userRepository.save(user);
    }

    @Override
    public AppUser updateUser(Long id, AppUser user) {
        AppUser savedUser = findUserById(id);
        savedUser.setName(user.getName());
        savedUser.setLogin(user.getLogin());
        savedUser.setGroup(user.getGroup());
        savedUser.setRole(user.getRole());
        return savedUser;
    }

    @Override
    public void changePassword(ChangePasswordForm changePasswordForm) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUser user = findUserByUsername(username);
        if(!passwordEncoder.matches(user.getPassword(), changePasswordForm.getOldPassword()))
            throw new IllegalArgumentException("Incorrect Password");
        if(!changePasswordForm.getNewPassword().equals(changePasswordForm.getRePassword()))
            throw new IllegalArgumentException("Password Not Confirmed");
        user.setPassword(passwordEncoder.encode(changePasswordForm.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void resetPassword(Long id) {
        AppUser user = findUserById(id);
        user.setPassword(passwordEncoder.encode(user.getLogin()));
        user.setStatus(UserStatus.initial);
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
