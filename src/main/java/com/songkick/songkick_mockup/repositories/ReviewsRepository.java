package com.songkick.songkick_mockup.repositories;

import com.songkick.songkick_mockup.models.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends CrudRepository<Review,Long> {}