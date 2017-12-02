
package com.songkick.songkick_mockup.controllers;

import com.songkick.songkick_mockup.models.FriendRequest;
import com.songkick.songkick_mockup.models.User;
import com.songkick.songkick_mockup.repositories.FriendsRepository;
import com.songkick.songkick_mockup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.util.List;

@Controller
public class FriendsController {
    private FriendsRepository friendsRepository;
    private UsersRepository usersRepository;


    @Autowired
    public FriendsController(FriendsRepository friendsRepository, UsersRepository usersRepository) {
        this.friendsRepository = friendsRepository;
        this.usersRepository = usersRepository;

    }


    @GetMapping("/request/{id}")
    public String showUser(@PathVariable long id, Model model) {

        User sender = usersRepository.findOne(id);
        model.addAttribute("sender", sender);

        return "users/request";
    }

    @PostMapping("/request/{id}")
    public String sendRequestPage(@PathVariable long id, Model model) {
        FriendRequest friendRequests = new FriendRequest();
        // using the id in the path
        // create and save req in the db
        User sender = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User newFriend = usersRepository.findOne(id);// query the database

        friendRequests.setSender(sender);
        friendRequests.setReciever(newFriend);
        friendsRepository.save(friendRequests);
        model.addAttribute("user", newFriend.getId());
        model.addAttribute("sender", sender.getId());
        return "redirect:/";
    }

    @GetMapping("/response/{id}")
    public String receiveRequest(@PathVariable long id,Model model){
        User receiver = usersRepository.findOne(id);
        List<FriendRequest> friendRequests = friendsRepository.findByReceiver(receiver);
        model.addAttribute("requests", friendRequests);
        model.addAttribute("user", receiver);
        return "users/response";
    }


    @PostMapping("/response/{id}")
    public String postResponse (@PathVariable long id, Model model){
        User sender = usersRepository.findOne(id);
        model.addAttribute("user", sender);
        return "redirect:/profile";

    }


    @PostMapping("/request/approve/{id}")
    public String createFriendship(@PathVariable long id) {
        // find the request in the database using the id, and the repository
        FriendRequest request = friendsRepository.findOne(id);
        request.setApproval(true);
        // request set the approval to true

        // save the request in the database
        friendsRepository.save(request);

        return "redirect:/profile";


    }

    @PostMapping("/request/ignore/{id}")
    public String declineFriendship(@PathVariable long id) {
        // find the request in the database using the id, and the repository
        FriendRequest request = friendsRepository.findOne(id);
        request.setApproval(false);
        // request set the approval to true

        // save the request in the database
        friendsRepository.save(request);

        return "redirect:/profile";


    }




}

