package com.leoni.packaging.service;

import com.leoni.packaging.dto.ChangePasswordForm;
import com.leoni.packaging.enums.UserStatus;
import com.leoni.packaging.model.AppUser;
import org.springframework.data.domain.Page;

public interface UserService {
    AppUser findUserByUsername(String username);
    AppUser findUserById(Long id);
    Page<AppUser> findUser(String search, int page, int size);
    AppUser addUser(AppUser user);
    AppUser updateUser(Long id, AppUser user);
    void changePassword(ChangePasswordForm changePasswordForm);
    void resetPassword(Long id);
    void deleteUserById(Long id);
}
