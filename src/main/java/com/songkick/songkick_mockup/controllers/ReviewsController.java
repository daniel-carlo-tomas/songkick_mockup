package com.songkick.songkick_mockup.controllers;

import com.songkick.songkick_mockup.models.Review;
import com.songkick.songkick_mockup.models.User;
import com.songkick.songkick_mockup.repositories.ReviewsRepository;
import com.songkick.songkick_mockup.repositories.UsersRepository;
import com.songkick.songkick_mockup.services.ReviewSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ReviewsController {
    @Autowired
    private final ReviewsRepository reviewsRepository;
    @Autowired
    private final UsersRepository usersRepository;

    private final ReviewSvc reviewSvc;

    @Autowired
    public ReviewsController(ReviewsRepository reviewsRepository, UsersRepository usersRepository, ReviewSvc reviewSvc) {
        this.reviewsRepository = reviewsRepository;
        this.usersRepository = usersRepository;
        this.reviewSvc = reviewSvc;
    }

    @GetMapping("review/create")
    public String showReviewForm (Model model) {
        model.addAttribute("review", new Review());
        return "reviews/create";
    }

    @PostMapping("review/create")
    public String submitReviewForm(@Valid Review review, Errors validation, Model model) {

        if (validation.hasErrors()) {
            model.addAttribute(validation);
            model.addAttribute(review);
            return "review/create";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        review.setUser(user);

        reviewsRepository.save(review);
        return "redirect:/profile";
    }

    @GetMapping("review/show")
    public String showUserReviews(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Review> reviews = reviewsRepository.findAllByUser(user);
        model.addAttribute("reviews", reviews);
        return "/reviews/showReviews";
    }

    @PostMapping("review/{id}/delete")
    public String deleteReview(@PathVariable long id) {
        Review review = reviewsRepository.findOne(id);
        User user = review.getUser();
        if (!reviewSvc.userMatch(user)) {
            return "redirect:/reviews/show";
        }
        reviewsRepository.delete(id);

        return "redirect:/profile";
    }
}
