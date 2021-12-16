package com.github.Task_312.controller;

import com.github.Task_312.model.User;


import com.github.Task_312.service.RoleService;
import com.github.Task_312.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private RoleService roleService;
    private UserService userService;

    @Autowired
    public AdminController(RoleService roleService, UserService userService){
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("")
    public String getUserManagement(@AuthenticationPrincipal User activeuser, Model model){
        model.addAttribute("activeuser", activeuser);
        model.addAttribute("users",userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/userManagement";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.removeUser(id);
        return "redirect:..";
    }

    @PostMapping("")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam(value = "roleId", required = false) Long[] roleId) {
        if(roleId !=null) {
            user.getRoleSet().addAll( Arrays.stream(roleId).map(id->roleService.getRoleById(id)).collect(Collectors.toSet()) );
        }
        userService.addUser(user);
        return "redirect:./admin";
    }

    @PatchMapping("editUser/{id}")
    public String editUser(@ModelAttribute("user") User user,
                           @RequestParam(value = "roleId", required = false) Long[] roleId) {
        if(roleId !=null) {
            user.getRoleSet().addAll( Arrays.stream(roleId).map(id->roleService.getRoleById(id)).collect(Collectors.toSet()) );
        }
        userService.updateUser(user);
        return "redirect:..";
    }

}