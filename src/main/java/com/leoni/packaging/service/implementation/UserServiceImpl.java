package com.leoni.packaging.service.implementation;

import com.leoni.packaging.dao.UserRepository;
import com.leoni.packaging.dto.ChangePasswordForm;
import com.leoni.packaging.model.AppUser;
import com.leoni.packaging.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public AppUser findUserByUsername(String username) {
        return null;
    }

    @Override
    public Page<AppUser> findAppUser(String search, int page, int size) {
        return null;
    }

    @Override
    public AppUser addAppUser(AppUser user) {
        return null;
    }

    @Override
    public AppUser updateAppUser(Long id, AppUser user) {
        return null;
    }

    @Override
    public void changePassword(ChangePasswordForm changePasswordForm) {

    }

    @Override
    public void deleteAppUserById(Long id) {

    }
}
