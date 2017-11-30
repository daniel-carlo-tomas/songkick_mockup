package com.songkick.songkick_mockup.controllers;

import com.songkick.songkick_mockup.models.Review;
import com.songkick.songkick_mockup.models.User;
import com.songkick.songkick_mockup.repositories.ReviewsRepository;
import com.songkick.songkick_mockup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReviewsController {
    @Autowired
    private ReviewsRepository reviewsRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    public ReviewsController (ReviewsRepository reviewsRepository, UsersRepository usersRepository) {
        this.reviewsRepository = reviewsRepository;
        this.usersRepository = usersRepository;
    }

    @GetMapping("review/create")
    public String showReviewForm (Model model) {
        model.addAttribute("review", new Review());
        return "reviews/createReview";
    }

    @PostMapping("review/create")
    public String submitReviewForm (Review review, Model model) {
        User user = usersRepository.findByUsername("carlooo");
        if (user == null) {
            return "/users/login";
        }

        review.setUser(user);

        List<Review> reviews = user.getReviews();
        reviews.add(review);
        user.setReviews(reviews);
        usersRepository.save(user);
        model.addAttribute("user", user);
        return "/reviews/showReviews";
    }

    @GetMapping("review/show")
    public String showUserReviews (Model model) {
        User user = usersRepository.findByUsername("carlooo");
        model.addAttribute("user", user);
        return "/reviews/showReviews";
    }

    @PostMapping("review/{id}/delete")
    public String deleteReview (@PathVariable long id) {
        reviewsRepository.delete(id);

        return "redirect:/review/show";
    }
}
