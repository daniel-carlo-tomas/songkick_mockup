package com.songkick.songkick_mockup.services;

import com.songkick.songkick_mockup.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("ReviewSvc")
public class ReviewSvc {
    public boolean userMatch (User user) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) return  false;
        User loggedIn = (User) principal;
        if (user.getUsername().equals(loggedIn.getUsername())) {
            return true;
        } else {
            return false;
        }
    }
}
