package com.leoni.packaging.web;

import com.leoni.packaging.dto.ChangePasswordForm;
import com.leoni.packaging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SecurityController {
    private final UserService userService;

    @GetMapping(path = "/login")
    public String loginPage(){
        return "loginPage";
    }

    @GetMapping("/change-password")
    public String changePasswordPage(Model model){
        model.addAttribute("changePasswordForm", new ChangePasswordForm());
        return "changePassword";
    }

    @PostMapping("/change-password")
    public String changePassword(ChangePasswordForm form){
        userService.changePassword(form);
        return "redirect:/";
    }

}
