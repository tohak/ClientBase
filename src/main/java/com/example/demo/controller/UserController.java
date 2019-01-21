package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRole;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String userList(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        List<User> users= userService.getUsers(filter);
        if (users.isEmpty()){
            model.addAttribute("message", "Your search for nothing was found.");
        }else {
            model.addAttribute("users", users);
            model.addAttribute("filter", filter);
        }
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')") // обозначить прова только админам
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", UserRole.values());

        return "userEdit";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String login,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.saveUser(user, login, form);
        return "redirect:/user";
    }
}
