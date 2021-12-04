package com.github.Task_311.controller;

import com.github.Task_311.model.User;


import com.github.Task_311.service.RoleService;
import com.github.Task_311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getUserManagement(Model model){
        model.addAttribute("users",userService.getAllUsers());
        model.addAttribute("newUser", new User("USERNAME", "PasSword1!", "USER@MAIL.DOM", "NAME", "LASTNAME"));
        model.addAttribute("roles", roleService.getAllRoles());

        return "admin/userManagement";
    }

    @GetMapping ("editUser/{id}")
    public String getEditUser(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/editUser";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.removeUser(id);
        return "redirect:..";
    }

    @PostMapping("")
    public String addUser(@ModelAttribute("newUser") User user, @RequestParam("roleId") Long[] roleId) {
        user.getRoleSet().addAll( Arrays.stream(roleId).map(id->roleService.getRoleById(id)).collect(Collectors.toSet()) );
        userService.addUser(user);
        return "redirect:./admin";
    }

    @PostMapping("editUser/{id}")
    public String editUser(@ModelAttribute("user") User user, @RequestParam("roleId") Long[] roleId) {
        user.getRoleSet().addAll(Arrays.stream(roleId).map(id->roleService.getRoleById(id)).collect(Collectors.toSet()));
        userService.updateUser(user);
        return "redirect:..";
    }

}