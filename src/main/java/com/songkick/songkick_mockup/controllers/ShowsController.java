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


    @GetMapping("/show/{id}/moreInfo")
    public String showMore(@PathVariable long id, Model model) {
        Show show = showsRepository.findOne(id);
        model.addAttribute("show", show);
        return "/shows/viewIndividualShow";
    }


    @RequestMapping(value="/show/add", method= RequestMethod.POST)
    public String saveShow(@RequestParam("id") Long showId, @RequestParam("artists") String artists, @RequestParam("venue") String venue, Model model) {
        System.out.println(showId);

        // VALIDATE SHOW ISNT ALREADY IN DB

        Show show = showsRepository.findOne(showId);
        if (show == null) {

            // ADD TO SHOWS TABLE

            show = new Show();
            show.setId(showId);
            show.setArtists(artists);
            show.setVenue(venue);
            showsRepository.save(show);
        }

        // SAVE TO USER SHOWS

        User user = usersRepository.findByUsername("carlooo");
        List<Show> shows = user.getShows();
        shows.add(show);
        user.setShows(shows);
        usersRepository.save(user);
        model.addAttribute("user", user);

        return "/users/shows";
    }

    @PostMapping("show/{id}/delete")
    public String deleteShow(@PathVariable long id, Model model) {
        Show show = showsRepository.findOne(id);
        User user = usersRepository.findByUsername("carlooo");
        List<Show> shows = user.getShows();
        shows.remove(show);
        user.setShows(shows);
        usersRepository.save(user);
        model.addAttribute(user);
        return "/users/shows";
    }

}
