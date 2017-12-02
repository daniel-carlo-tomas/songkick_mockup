

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
            value = "select u.id, f.receiver_id, f.sender_id from users as u join friendrequests as f on u.id = f.sender_id where f.approval = true")
    List<FriendRequest> showFriendsList(User friend);

    @Query(nativeQuery = true,
            value = "select u.username, f.sender_id ,f.receiver_id from users as u join friendrequests as f on u.id = f.sender_id where u.id like ?1")
    List<FriendRequest> showPendingRequests(User friend);

//    List<FriendRequest> findByApproval(boolean value);



    }

