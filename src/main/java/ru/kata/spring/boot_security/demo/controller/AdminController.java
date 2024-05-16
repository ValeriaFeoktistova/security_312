package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.UserRepo.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleDao roleDao;

    @Autowired
    public AdminController(UserService userService, RoleDao roleDao) {
        this.userService = userService;
        this.roleDao = roleDao;
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        List<Role> roles = roleDao.findAll();
        model.addAttribute("roles", roles);
        return "user-info";
    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("username") String name, @RequestParam("roleIds") List<Long> roleIds) {
        List<Role> rolesAll = userService.getRolesById(roleIds);
        user.setName(name);
        user.setRoles(rolesAll);
        userService.createOreUpdateUser(user);
        return "redirect:/admin/admin";
    }

    @RequestMapping("/editUser")
    public String update(@RequestParam("userId") long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleDao.findAll());
        return "/user-info";

    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam("roleIds") List<Long> roleIds) {
        List<Role> rolesAll = userService.getRolesById(roleIds);
        user.setRoles(rolesAll);
        userService.createOreUpdateUser(user);
        return "redirect:/admin/admin";
    }

    @RequestMapping("/delete")
    public String deleteUser(@RequestParam("userId") long id, Model model) {
        model.addAttribute("user", userService.deleteUser(id));
        return "redirect:/admin/admin";
    }
}




