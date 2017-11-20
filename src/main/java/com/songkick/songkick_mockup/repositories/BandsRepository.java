package com.songkick.songkick_mockup.repositories;
import com.songkick.songkick_mockup.models.Band;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandsRepository extends CrudRepository<Band, Long> {
}
