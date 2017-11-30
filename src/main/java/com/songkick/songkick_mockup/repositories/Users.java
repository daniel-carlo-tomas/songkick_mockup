package com.songkick.songkick_mockup.repositories;

import com.songkick.songkick_mockup.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Users extends CrudRepository<User, Long> {
     User findByUsername(String username);
}