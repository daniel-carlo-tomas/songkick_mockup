package com.songkick.songkick_mockup.controllers;

import com.songkick.songkick_mockup.models.User;
import com.songkick.songkick_mockup.repositories.BandsRepository;
import com.songkick.songkick_mockup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class UsersController {

    @Autowired
    private UsersRepository userRepository;
    private BandsRepository bandsRepository;
    private PasswordEncoder passwordEncoder;

    public UsersController(UsersRepository userRepository, BandsRepository bandsRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bandsRepository = bandsRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/register")
    public String ShowRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid User user, Errors validation, Model model) {



        if (validation.hasErrors()) {
            model.addAttribute(validation);
            model.addAttribute(user);
            return "users/register";
        }

        String hash = passwordEncoder.encode((user.getPassword()));
        user.setPassword(hash);
        userRepository.save(user);

        return "redirect:/login";
    }


    @GetMapping("/users/showUsers")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/showUsers";
    }

    @GetMapping("users/bands")
    public String showUsersBands() {
        return "users/bands";
    }

    @GetMapping("/users/showIndividualUser/{id}")
    public String showIndividualUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userRepository.findOne(id));
        model.addAttribute("users", userRepository.findAll());
        return "users/showIndividualUser";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userRepository.findOne(user.getId());

        model.addAttribute("user", user);

        return "users/profile";
    }
}
