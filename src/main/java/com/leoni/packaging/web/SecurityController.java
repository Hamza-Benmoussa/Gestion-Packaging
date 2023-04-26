package com.leoni.packaging.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping(path = "/login")
    public String loginPage(){
        return "loginPage";
    }

}
