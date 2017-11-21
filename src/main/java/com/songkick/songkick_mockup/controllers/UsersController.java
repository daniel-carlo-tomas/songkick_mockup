package com.songkick.songkick_mockup.controllers;

import com.songkick.songkick_mockup.models.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import com.songkick.songkick_mockup.repositories.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UsersController {

    @Autowired
    private UsersRepository userRepository;

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
    public String submitLoginForm(User user, Model model) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            User user2 = userRepository.findByUsername(user.getUsername());
            if (user.getPassword().equals(user2.getPassword())) {
                model.addAttribute("user", user);
                return "/success";
            } else {
                return "/failure";
            }
        } else return "/failure";
    }

}
