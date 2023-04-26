package com.leoni.packaging.security;

import com.leoni.packaging.model.AppUser;
import com.leoni.packaging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = null;
        try{
            user = userService.findUserByUsername(username);
            return user;
        }catch (NoSuchElementException e){
            throw new UsernameNotFoundException(e.getMessage(),e);
        }
    }
}
