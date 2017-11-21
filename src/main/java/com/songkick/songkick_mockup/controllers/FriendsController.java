
package com.songkick.songkick_mockup.controllers;

import com.songkick.songkick_mockup.models.FriendRequests;
import com.songkick.songkick_mockup.models.User;
import com.songkick.songkick_mockup.repositories.FriendsRepository;
import com.songkick.songkick_mockup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class FriendsController {
    private FriendsRepository friendsRepository;
    private UsersRepository usersRepository;


    @Autowired
    public FriendsController(FriendsRepository friendsRepository, UsersRepository usersRepository) {
        this.friendsRepository = friendsRepository;
        this.usersRepository = usersRepository;

    }

    @GetMapping("/test")
    public String test(Model model){
        User sender = usersRepository.findOne(4l);
        model.addAttribute("user", sender);
        return "users/follow";

    }

    @PostMapping("/request/follow/{id}")
    public String sendRequestMessage(@PathVariable long id, Model model) {
        FriendRequests friendRequests = new FriendRequests();
        User newFriend = usersRepository.findOne(id);// query the database
        // using the id in the path
        // create and save req in the db
        User sender = usersRepository.findOne(1l);
        friendRequests.setSender(sender);
        friendRequests.setReciever(newFriend);

        friendsRepository.save(friendRequests);
        return "redirect:/";
    }

    @PostMapping("/follow/approve/{id}")
    public String createFriendship(@PathVariable long id) {
        // find the request in the database using the id, and the repository
        FriendRequests request = friendsRepository.findOne(id);
        request.setApproval(true);
        // request set the approval to true

        // save the request in the database
        friendsRepository.save(request);

        return "redirect:/";


    }

    @PostMapping("/follow/ignore/{id}")
    public String declineFriendship(@PathVariable long id) {
        // find the request in the database using the id, and the repository
        FriendRequests request = friendsRepository.findOne(id);
        request.setApproval(false);
        // request set the approval to true

        // save the request in the database
        friendsRepository.save(request);

        return "redirect:/";


    }

}

