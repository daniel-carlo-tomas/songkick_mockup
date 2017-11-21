

package com.songkick.songkick_mockup.repositories;

import com.songkick.songkick_mockup.models.FriendRequests;
import com.songkick.songkick_mockup.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface FriendsRepository extends CrudRepository<FriendRequests, Long> {
        //FriendRequests findOne(Long id);
        List<FriendRequests> findByReceiver(User receiver);
    }

