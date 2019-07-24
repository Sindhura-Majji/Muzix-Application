package com.stackroute.Muzix.repository;


import com.stackroute.Muzix.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//This inherits JpaRepository
@Repository
public interface MuzixRepository extends JpaRepository<Track, Integer> {
}
