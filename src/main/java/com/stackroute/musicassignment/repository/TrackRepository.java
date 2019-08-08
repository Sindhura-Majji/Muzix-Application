package com.stackroute.musicassignment.repository;

import com.stackroute.musicassignment.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//TrackRepository extends MongoRepository
@Repository
public interface TrackRepository extends MongoRepository<Track,Integer> {
  
}
