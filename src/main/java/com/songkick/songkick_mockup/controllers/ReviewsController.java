package com.songkick.songkick_mockup.controllers;

import com.songkick.songkick_mockup.models.Reviews;
import com.songkick.songkick_mockup.models.User;
import com.songkick.songkick_mockup.repositories.ReviewsRepository;
import com.songkick.songkick_mockup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewsController {
    private ReviewsRepository reviewsRepository;
    private UsersRepository usersRepository;

    @Autowired
    public ReviewsController (ReviewsRepository reviewsRepository, UsersRepository usersRepository) {
        this.reviewsRepository = reviewsRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping("create/review")
    public String showReviewForm (Model model) {
        model.addAttribute("review", new Reviews());
        return "reviews/create-review";
    }

    @PostMapping("create/review")
    public String submitReviewForm (Reviews review) {
        User user = usersRepository.findByUsername("carlo");
        if (user == null) {
            return "/users/login";
        }
        review.setUser(user);
        reviewsRepository.save(review);
        return "/success";
    }
}
