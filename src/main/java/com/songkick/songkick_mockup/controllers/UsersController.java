package com.songkick.songkick_mockup.controllers;

import com.songkick.songkick_mockup.models.User;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import com.songkick.songkick_mockup.repositories.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UsersController {

    private UsersRepository userRepository;


    @Autowired
    public UsersController(UsersRepository userRepository) {
        this.userRepository = userRepository;

    }


    @GetMapping("/register")
    public String ShowRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(User user) {
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }

    @PostMapping("/login")
    public String submitLoginForm(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            User user2 = userRepository.findByUsername(user.getUsername());
            if (user.getPassword().equals(user2.getPassword())) {
                return "/success";
            } else {
                return "/failure";
            }
        } else return "/failure";
    }
}
