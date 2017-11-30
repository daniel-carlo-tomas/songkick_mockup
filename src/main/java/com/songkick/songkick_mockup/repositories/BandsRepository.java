package com.songkick.songkick_mockup.repositories;
import com.songkick.songkick_mockup.models.Band;
import com.songkick.songkick_mockup.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandsRepository extends CrudRepository<Band, Long> {
   @Query(nativeQuery = true,
           value = "select * from bands_users where user_id like ?")
    List<Band> listUsersBands(User user);
}
