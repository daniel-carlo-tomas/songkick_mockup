package com.songkick.songkick_mockup.controllers;

import com.songkick.songkick_mockup.models.Band;
import com.songkick.songkick_mockup.models.User;
import com.songkick.songkick_mockup.repositories.BandsRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import com.songkick.songkick_mockup.repositories.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class UsersController {

    @Autowired
    private UsersRepository userRepository;
    private BandsRepository bandsRepository;

    public UsersController(UsersRepository userRepository, BandsRepository bandsRepository) {
        this.userRepository = userRepository;
        this.bandsRepository = bandsRepository;
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
            List<User> users = (List<User>) userRepository.findAll();
            User user2 = userRepository.findByUsername(user.getUsername());


            List <Band> bands = (List<Band>) bandsRepository.findAll();
            List<Band> usersBands = bandsRepository.listUsersBands(user2);

            if (user.getPassword().equals(user2.getPassword())) {
                model.addAttribute("users", users);
                model.addAttribute("user", user2);
                model.addAttribute("bands", bands);
                model.addAttribute("userBandList", usersBands);
//                model.addAttribute("band", band);
                return "/users/profile";
            } else {
                return "/failure";
            }
        } else return "/failure";
    }

    @GetMapping("/users/show/{id}")
    public String showProfile (@PathVariable long id, Model model) {
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);
        return "users/show";
    }
}
