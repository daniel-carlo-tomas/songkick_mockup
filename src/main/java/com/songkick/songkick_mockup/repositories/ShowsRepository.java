package com.songkick.songkick_mockup.repositories;

import com.songkick.songkick_mockup.models.Show;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowsRepository extends CrudRepository<Show, Long> {
}
