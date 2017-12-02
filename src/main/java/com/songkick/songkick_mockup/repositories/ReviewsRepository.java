package com.songkick.songkick_mockup.repositories;

import com.songkick.songkick_mockup.models.Review;
import com.songkick.songkick_mockup.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends CrudRepository<Review, Long> {
    public List<Review> findAllByUser(User user);
}