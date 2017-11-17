package com.songkick.songkick_mockup.controllers;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.songkick.songkick_mockup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UsersController {

    private UsersRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired

}
