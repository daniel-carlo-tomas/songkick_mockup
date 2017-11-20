
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

    @GetMapping("follow")
    public String sendFollowMessage(Model model) {
        FriendRequests friendRequests = new FriendRequests();
        model.addAttribute("friendRequest", friendRequests);
        return "follow-form";
    }

    @PostMapping("follow/{id}")
    public String followUser(@PathVariable int id, FriendRequests request, FriendsRepository friendsRepository) {
        User user = usersRepository.findOne(((long) id));


        if (request.isApproval()) {
            request.setApproval(true);
            request.setSender(usersRepository.findByUsername("carlo"));
            request.setReciever(user);
            friendsRepository.save(request);

            return "redirect:/userProfile";

        }
        return "userProfile";


    }

}

