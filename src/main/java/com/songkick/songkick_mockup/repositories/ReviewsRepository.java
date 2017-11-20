package com.songkick.songkick_mockup.repositories;

import com.songkick.songkick_mockup.models.Reviews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends CrudRepository<Reviews,Long> {}