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
    public UsersController( UsersRepository userRepository){
        this.userRepository = userRepository;

    }


    @GetMapping("/register")
    public String ShowRegisterForm(Model model){
        User user = new User();
        user.setUsername("carlo");
        user.setEmail("carlo@codeup.com");
        user.setPassword("$$$");
        user.setCity("San Antonio");
        user.setState("Texas");
        userRepository.save(user);
        return "/users/register";
    }

//    @PostMapping("/register")
//    public String saveUser( User user) {
//    userRepository.save(user);
//
//        return "redirect/login";
//
//
//
//
//    }
}
