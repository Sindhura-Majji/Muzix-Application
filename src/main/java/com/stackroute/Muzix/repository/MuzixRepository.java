package com.stackroute.Muzix.repository;


import com.stackroute.Muzix.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//This inherits JpaRepository
@Repository
public interface MuzixRepository extends JpaRepository<Track,Integer> {

    @Query(value= "select * from Track where trackName=?1",nativeQuery = true)
    List<Track> findTitleByName(String trackName);
}
