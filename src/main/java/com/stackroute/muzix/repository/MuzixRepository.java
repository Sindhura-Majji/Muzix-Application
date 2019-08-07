package com.stackroute.muzix.repository;


import com.stackroute.muzix.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Extending JpaRepository
@Repository
public interface MuzixRepository extends JpaRepository<Track, Integer> {
}
