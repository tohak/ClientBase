package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.errors.ControllerErrorHandling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final RestTemplate restTemplate;

    private static final String REG = "registration";

    public RegistrationController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/registration")
    public String registration() {
        return REG;
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfim,
            @RequestParam("married") String married,
            @RequestParam("educations") String educations,
            @RequestParam("dateusr") String date,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfim);
        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Password confirmation cannot be empty");
        }
        if (user.getPassword() != null && user.getPassword().equals(passwordConfim)) {
            model.addAttribute("passwordError", "password are different!");
        }
        if (isConfirmEmpty || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerErrorHandling.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return REG;
        }
        if (!userService.addUser(user, married, educations, date)) {
            model.addAttribute("userloginError", "User exists or wrong user name!");
            return REG;
        }

        return "login";
    }
}
