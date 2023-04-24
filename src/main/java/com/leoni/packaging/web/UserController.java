package com.leoni.packaging.web;

import com.leoni.packaging.model.AppUser;
import com.leoni.packaging.service.RouteService;
import com.leoni.packaging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class UserController {
//    public final UserService userService;

    @GetMapping(path = "users")
    public String users(Model model, @RequestParam(name="search",required = false,defaultValue = "") String search){
        model.addAttribute("search", search);
        return "users";
    }
    @GetMapping(path = "add-user")
    public String userForm(Model model){
        model.addAttribute("users", new AppUser());
        return "addUser";
    }
    @PostMapping(path = "add-user")
    public String addUser(Model model, AppUser appUser){
        return "redirect:/users";
    }
    @PostMapping(path = "add-user/{userId}")
    public String updateUser(Model model, AppUser appUser, @PathVariable("userId") Long userId){
        return "redirect:/users";
    }
    @PostMapping(path = "delete-user/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId){
        return "redirect:/users";
    }
}
