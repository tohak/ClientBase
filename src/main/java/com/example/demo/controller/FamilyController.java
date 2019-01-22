package com.example.demo.controller;

import com.example.demo.domain.Family;
import com.example.demo.service.FamilyService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        if (login.isEmpty() || userService.getUserByLogin(login) == null) {
            model.addAttribute("loginError", "need input login");
            return "familyList";
        } else {
            Family family = familyService.saveFamily(new Family());
            if (userService.addUserFromFamily(family, login)) {
                return "redirect:/family";
            } else {
                model.addAttribute("loginError", "Login is not correct");
                return "familyList";
            }
        }

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{family}")
    public String userEditForm(@PathVariable Family family, Model model) {
        model.addAttribute("family", family);
        return "familyEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("familyEdit")
    public String familySave(@RequestParam("familyId") Family family,
                             @RequestParam(name = "toDelete[]", required = false, defaultValue = "") int[] toDelete) {
        if (toDelete == null) {
            return "family";
        }
        userService.deleteUserOnFamily(family, toDelete);
        return "redirect:/family";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("familyAddUser")
    public String familyAddUser(@RequestParam("familyId") Family family,
                                @RequestParam String login, Model model) {
        if (login.isEmpty() || userService.getUserByLogin(login) == null) {
            model.addAttribute("loginError", "need input login");
            return "redirect:/family";
        } else {
            if (userService.addUserFromFamily(family, login)) {
                return "redirect:/family";
            } else {
                model.addAttribute("loginError", "Login is not correct");
                return "redirect:/family";
            }
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("familyDelete")
    public String familyDeleteUser(@RequestParam("familyId") Family family) {
        userService.deleteFamily(family);
        return "redirect:/family";
    }
}
