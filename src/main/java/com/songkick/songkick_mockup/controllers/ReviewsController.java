package com.songkick.songkick_mockup.controllers;

import com.songkick.songkick_mockup.models.Reviews;
import com.songkick.songkick_mockup.repositories.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewsController {
    private ReviewsRepository reviewsRepository;

    @Autowired
    public ReviewsController (ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    @GetMapping("create/review")
    public String showReviewForm (Model model) {
        model.addAttribute("review", new Reviews());
        return "reviews/create-review";
    }

    @PostMapping("create/review")
    public String submitReviewForm (Reviews review) {
        reviewsRepository.save(review);
        return "/success";
    }
}
