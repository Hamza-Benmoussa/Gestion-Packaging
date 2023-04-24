package com.leoni.packaging.service;

import com.leoni.packaging.dto.ChangePasswordForm;
import com.leoni.packaging.model.AppUser;
import org.springframework.data.domain.Page;

public interface UserService {
    AppUser findUserByUsername(String username);
    Page<AppUser> findAppUser(String search, int page, int size);
    AppUser addAppUser(AppUser user);
    AppUser updateAppUser(Long id, AppUser user);
    void changePassword(ChangePasswordForm changePasswordForm);
    void deleteAppUserById(Long id);
}
