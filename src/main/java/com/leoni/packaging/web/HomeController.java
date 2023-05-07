package com.leoni.packaging.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    @GetMapping
    public String index(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication !=null && !(authentication instanceof AnonymousAuthenticationToken)) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                String authorityName = authority.getAuthority();
                if(authorityName.equals("ADMIN"))
                    return "redirect:/admin/statistics";
                if(authorityName.equals("USER"))
                    return "redirect:/user/scan";
            }
        }
        return "redirect:/login";
    }

}
