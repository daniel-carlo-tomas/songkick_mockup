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

    @Query(nativeQuery = true,
            value = "SELECT * FROM users WHERE username LIKE ?")
    List<User> searchUser(String term);


    @Query("select s from FriendRequest f join f.receiver r join f.sender s where r.id = ?1 and f.approval = true")
    List<User> friendsOf(Long userId);
}



