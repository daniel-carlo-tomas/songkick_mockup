package com.songkick.songkick_mockup.controllers;

import com.songkick.songkick_mockup.models.Band;
import com.songkick.songkick_mockup.models.User;
import com.songkick.songkick_mockup.repositories.BandsRepository;
import com.songkick.songkick_mockup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("band/search")
    public String showJSON() {
        return "/search/bandSearch";
    }

//    CHANGE MAPPING TO HAVE THE MAIN PART FIRST i.e. BAND/ADD not ADD/BAND...BAND/SEARCH

    @RequestMapping(value = "/band/add", method = RequestMethod.POST)
    public String saveBand(@RequestParam("jambase_id") Long bandId, @RequestParam("jambase_bandname") String bandname, Model model) {
        System.out.println(bandname);
        System.out.println(bandId);

        // VALIDATE THAT BAND ISNT ALREADY IN DB

        Band band = bandsRepository.findOne(bandId);
        if (band == null) {

            // SAVE THE BANDNAME + ID TO ThE BANDS TABLE

            band = new Band();
            band.setBandname(bandname);
            band.setJambaseId(bandId);
            bandsRepository.save(band);
        }

        // ADD THE BAND TO THE USERS LIST OF BANDS...HARD CODED AS CARLO FOR NOW

        // NEED TO VALIDATE THAT USER DOESNT ALREADY HAVE BAND IN LIST

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = usersRepository.findOne(user.getId());
        List<Band> bands = user.getBands();
        bands.add(band);
        user.setBands(bands);
        usersRepository.save(user);
//        model.addAttribute("user", user);

        return "redirect:/profile";
    }

    @GetMapping("/bandsProfile")
    public String showBandsProfile() {
        return "/bandsProfile";
    }

    @PostMapping("/band/{id}/delete")
    public String deleteFromUser(@PathVariable long id, Model model) {
        Band band = bandsRepository.findOne(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = usersRepository.findOne(user.getId());

        List<Band> bands = user.getBands();
        bands.remove(band);
        user.setBands(bands);
        usersRepository.save(user);
//        String msg = band + " removed from your list!";
////        model.addAttribute("user", user);
//        model.addAttribute("msg", msg);

//        bandsRepository.deleteUsersBand(id, user.getId());
        return "redirect:/profile";
    }


}
