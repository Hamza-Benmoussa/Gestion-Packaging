package com.leoni.packaging.web;

import com.leoni.packaging.enums.Role;
import com.leoni.packaging.model.AppUser;
import com.leoni.packaging.model.Group;
import com.leoni.packaging.service.GroupService;
import com.leoni.packaging.service.LineService;
import com.leoni.packaging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final GroupService groupService;
    private final LineService lineService;

    @GetMapping(path = "users")
    public String users(Model model,
                        @RequestParam(name="search",required = false, defaultValue = "") String search,
                        @RequestParam(name="page",required = false, defaultValue = "1") int page,
                        @RequestParam(name="size",required = false, defaultValue = "5") int size){
        page = page > 0 ? --page:0;
        Page<AppUser> user = userService.findUser(search, page, size);
        model.addAttribute("users", user.stream().toList());
        model.addAttribute("search", search);
        model.addAttribute("totalPagesArr",new int[user.getTotalPages()]);
        model.addAttribute("totalPages",user.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("currentSize",size);
        model.addAttribute("isFirstPage",user.isFirst());
        model.addAttribute("isLastPage",user.isLast());
        return "users";
    }
    @GetMapping(path = {"userForm", "userForm/","userForm/{userId}"})
    public String userForm(Model model, @PathVariable(name = "userId",required = false) Optional<Long> userId){
        List<Group> groups = groupService.findGroups();
        List<Role> roles = Arrays.stream(Role.values()).toList();
        model.addAttribute("groups",groups);
        model.addAttribute("roles",roles);
        model.addAttribute("lines", lineService.findAll());
        if(userId.isPresent()){
            AppUser user = userService.findUserById(userId.get());
            model.addAttribute("user", user);
        }else{
            model.addAttribute("user", new AppUser());
        }
        return "addUser";
    }
    @PostMapping(path = "user")
    public String postUser(AppUser user){
        if(user.getId()!=null){
            userService.updateUser(user.getId(),user);
        }else{
            userService.addUser(user);
        }
        return "redirect:/admin/users";
    }

    @PostMapping(path = "deleteUser/{userId}")
    public String deleteRoute(@PathVariable("userId") Long userId){
        userService.deleteUserById(userId);
        return "redirect:/admin/users";
    }

}
