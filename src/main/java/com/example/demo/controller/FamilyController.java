package com.example.demo.controller;

import com.example.demo.domain.Family;
import com.example.demo.domain.User;
import com.example.demo.service.FamilyService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/family")
public class FamilyController {
    private final FamilyService familyService;
    private final UserService userService;

    @Autowired
    public FamilyController(FamilyService familyService, UserService userService) {
        this.familyService = familyService;
        this.userService = userService;
    }

    @GetMapping
    public String familyList(Model model) {
        List<Family> familyList = familyService.getAll();
        if (familyList.isEmpty()) {
            model.addAttribute("message", "Your search for nothing was found.");
        } else {
            model.addAttribute("familys", familyList);
        }
        return "familyList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String add(@RequestParam String login, Model model) {
        if (familyService.addNewFamily(userService.getUserByLogin(login))) {
            return "redirect:/family";
        } else {
            model.addAttribute("loginError", "Login is not correct");
            return "familyList";
        }

    }
}
