package com.songkick.songkick_mockup.controllers;

import com.songkick.songkick_mockup.models.Band;
import com.songkick.songkick_mockup.models.User;
import com.songkick.songkick_mockup.repositories.BandsRepository;
import com.songkick.songkick_mockup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BandsController {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private BandsRepository bandsRepository;

    public BandsController(UsersRepository usersRepository, BandsRepository bandsRepository) {
        this.usersRepository = usersRepository;
        this.bandsRepository = bandsRepository;
    }

    @GetMapping("/addband")
    public String addBandToFavorites () {

        User user = usersRepository.findByUsername("carlooo");
        List<Band> bands = (List<Band>) bandsRepository.findAll();
        System.out.println(user.getUsername());
        for (Band band: bands
             ) {
            System.out.println(band.getBandname());
        }
        user.setBands(bands);
        usersRepository.save(user);
        return "/success";
    }
}
