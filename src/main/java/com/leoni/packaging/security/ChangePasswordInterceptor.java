package com.leoni.packaging.security;

import com.leoni.packaging.enums.UserStatus;
import com.leoni.packaging.model.AppUser;
import com.leoni.packaging.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class ChangePasswordInterceptor implements HandlerInterceptor {
    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String name = authentication.getName();
            if(name.equals("anonymousUser"))
                return true;
            AppUser appUser = userService.findUserByUsername(name);
            if (appUser.getStatus() == UserStatus.initial) {
                response.sendRedirect("/change-password");
                return false;
            }
        }
        return true;
    }

}
