package com.songkick.songkick_mockup.controllers;

import com.songkick.songkick_mockup.models.Band;
import com.songkick.songkick_mockup.models.FriendRequest;
import com.songkick.songkick_mockup.models.User;
import com.songkick.songkick_mockup.repositories.BandsRepository;
import com.songkick.songkick_mockup.repositories.FriendsRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import com.songkick.songkick_mockup.repositories.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class UsersController {

    @Autowired
    private UsersRepository userRepository;
    private BandsRepository bandsRepository;
    private FriendsRepository friendsRepository;
    private PasswordEncoder passwordEncoder;

    public UsersController(UsersRepository userRepository, BandsRepository bandsRepository, PasswordEncoder passwordEncoder, FriendsRepository friendsRepository) {
        this.userRepository = userRepository;
        this.bandsRepository = bandsRepository;
        this.passwordEncoder = passwordEncoder;
        this.friendsRepository = friendsRepository;
    }

    @GetMapping("users/bands")
    public String showUsersBands() {
        return "users/bands";
    }

    @GetMapping("/register")
    public String ShowRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid User user, Errors validation, Model model, String username) {

        User exisitingUser = userRepository.findByUsername(user.getUsername());
        User exisitingEmail = userRepository.findByEmail(user.getEmail());


        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);

            return "users/register";
        }


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }

    //    @PostMapping("/login")
//    public String submitLoginForm(User user, Model model) {
//
//        if (userRepository.findByUsername(user.getUsername()) != null) {
//            List<User> users = (List<User>) userRepository.findAll();
//            User user2 = userRepository.findByUsername(user.getUsername());
//
//            if (user.getPassword().equals(user2.getPassword())) {
//                model.addAttribute("users", users);
//                model.addAttribute("user", user2);
//
////                model.addAttribute("band", band);
//                return "/users/profile";
//            } else {
//                return "/failure";
//            }
//        } else return "/failure";
//    }
    @GetMapping("/users/showUsers")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/showUsers";
    }

    @GetMapping("/users/showIndividualUser/{id}")
    public String showIndividualUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userRepository.findOne(id));
        return "users/showIndividualUser";
    }

    @GetMapping("/profile")
    public String profile(Model model) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //find all friendRequests based on the loggedInUser.
        FriendRequest friendRequest = friendsRepository.findOne(loggedInUser.getId());

        List<Band> bands = (List<Band>) bandsRepository.findAll();
        List<Band> usersBands = bandsRepository.listUsersBands(loggedInUser);

        model.addAttribute("myFriendsList", friendsRepository.showFriendsList(loggedInUser));
        model.addAttribute("myPendingFriendsList", friendsRepository.showPendingRequests(loggedInUser));
        model.addAttribute("loggedInUser", loggedInUser);
        model.addAttribute("bands", bands);
        model.addAttribute("userBandList", usersBands);
        return "users/profile";
    }

    @GetMapping("/users/searchUser")
    public String searchUser(@RequestParam String term, Model model) {
        model.addAttribute("searchedContent", userRepository.searchUser("%" + term + "%"));
        return "users/showUsers";
    }
}
