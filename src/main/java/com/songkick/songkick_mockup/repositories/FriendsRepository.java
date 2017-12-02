

package com.songkick.songkick_mockup.repositories;

import com.songkick.songkick_mockup.models.FriendRequest;
import com.songkick.songkick_mockup.models.FriendRequest;
import com.songkick.songkick_mockup.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface FriendsRepository extends CrudRepository<FriendRequest, Long> {
        //FriendRequests findOne(Long id);
        List<FriendRequest> findByReceiver(User receiver);

    List<FriendRequest> findBySender(User sender);

    @Query(nativeQuery = true,
            value = "SELECT u.id, f.receiver_id, f.sender_id FROM users AS u JOIN friendrequests AS f ON u.id = f.sender_id WHERE f.approval = TRUE")
    List<FriendRequest> showFriendsList(User friend);

    @Query(nativeQuery = true,
            value = "SELECT u.username, f.sender_id ,f.receiver_id FROM users AS u JOIN friendrequests AS f ON u.id = f.sender_id WHERE u.id LIKE ?1")
    List<FriendRequest> showPendingRequests(User friend);

//    List<FriendRequest> findByApproval(boolean value);



    }

