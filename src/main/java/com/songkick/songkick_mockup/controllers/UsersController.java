package com.songkick.songkick_mockup.controllers;

import com.songkick.songkick_mockup.models.Band;
import com.songkick.songkick_mockup.models.FriendRequest;
import com.songkick.songkick_mockup.models.Show;
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


    @GetMapping("users/bands")
    public String showUsersBands() {
        return "users/bands";
    }

    @GetMapping("/profile")
    public String profile(Model model) {


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userRepository.findOne(user.getId());
//        List<FriendRequest> approvedFriends = friendsRepository.showFriendsList(user);
//        List<FriendRequest> showFriendsList = friendsRepository.showPendingRequests(user);


//        model.addAttribute("approved", approvedFriends);
//        model.addAttribute("friends", showFriendsList);

        model.addAttribute("user", user);

        return "users/profile";
    }

    @GetMapping("/searchUser")
    public String searchUser(@RequestParam String term, Model model) {
        model.addAttribute("searchedContent", userRepository.searchUser("%" + term + "%"));
        return "users/showUsers";
    }

    @GetMapping("user/search")
    public String search() {
        return "search/searchUser";
    }

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


//    @RequestMapping(value="/friend/add", method= RequestMethod.GET)
//    public String saveFriend(@RequestParam("username") String username, @RequestParam("sender_id") long senderId, @RequestParam("receiver_id") String receiver, Model model) {
//        System.out.println(username);
//        System.out.println(senderId);
//        System.out.println(receiverID);
//
//     // method for friends in progress -carlo-work
//
//        Show show = showsRepository.findOne(showId);
//        if (show == null) {
//
//            // ADD TO SHOWS TABLE
//
//            show = new Show();
//            show.setId(showId);
//            show.setArtists(artists);
//            show.setVenue(venue);
//            showsRepository.save(show);
//        }
//
//        // SAVE TO USER SHOWS
//
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        user = usersRepository.findOne(user.getId());
//        List<Show> shows = user.getShows();
//        shows.add(show);
//        user.setShows(shows);
//        usersRepository.save(user);
////        model.addAttribute("user", user);
//
//        return "redirect:/profile";
//    }


}

