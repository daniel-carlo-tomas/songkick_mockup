package com.songkick.songkick_mockup.controllers;

import com.songkick.songkick_mockup.models.Show;
import com.songkick.songkick_mockup.models.User;
import com.songkick.songkick_mockup.repositories.ShowsRepository;
import com.songkick.songkick_mockup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/show/search")
    public String showJSON () {
        return "/search/showSearchByZipcode";
    }


    @GetMapping("/show/{show_id}/moreInfo")
    public String showMore (@PathVariable long show_id, Model model) {
        model.addAttribute("show_id", show_id);
        return "/shows/viewIndividualShow";
    }


    @RequestMapping(value="/show/add", method= RequestMethod.POST)
    public String saveShow (@RequestParam("show_id") Long showId) {
        System.out.println(showId);

        // VALIDATE SHOW ISNT ALREADY IN DB

        Show show = showsRepository.findOne(showId);
        if (show == null) {

            // ADD TO SHOWS TABLE

            show = new Show();
            show.setShow_id(showId);
            showsRepository.save(show);
        }

        // SAVE TO USER SHOWS

        User user = usersRepository.findByUsername("carlooo");
        List<Show> shows = user.getShows();
        shows.add(show);
        user.setShows(shows);
        usersRepository.save(user);

        return "redirect:/users/profile";
    }

}
