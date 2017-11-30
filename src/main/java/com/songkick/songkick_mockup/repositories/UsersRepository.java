package com.songkick.songkick_mockup.repositories;

import com.songkick.songkick_mockup.models.Band;
import com.songkick.songkick_mockup.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    @Query(nativeQuery = true,
            value = "select * from users where username like ?")
    List<User> searchUser(String term);



}

