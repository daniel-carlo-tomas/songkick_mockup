package com.songkick.songkick_mockup.controllers;

import com.songkick.songkick_mockup.models.Show;
import com.songkick.songkick_mockup.models.User;
import com.songkick.songkick_mockup.repositories.ShowsRepository;
import com.songkick.songkick_mockup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ShowsController {

    @Autowired
    private ShowsRepository showsRepository;
    @Autowired
    private UsersRepository usersRepository;

    public ShowsController(ShowsRepository showsRepository, UsersRepository usersRepository) {
        this.showsRepository = showsRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/show/show")
    public String findShow() {
        return "";
    }

    @GetMapping("show/search")
    public String showJSON () {
        return "/search/showSearch";
    }

    @GetMapping("/add/show")
    public String addShow() {

        User user = usersRepository.findByUsername("carlooo");
        List<Show> shows = (List<Show>) showsRepository.findAll();
        user.setShows(shows);
        usersRepository.save(user);
        return "/success";

    }

    @GetMapping("/viewEvents")
    public String viewEvent(){
        return "/viewEvents";
    }
}
