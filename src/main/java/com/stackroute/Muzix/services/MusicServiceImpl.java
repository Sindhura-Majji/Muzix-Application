package com.stackroute.Muzix.services;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzix.exceptions.TrackNotFoundException;
import com.stackroute.Muzix.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//This class will implement MusicService and overRide the methods
@Service
public class MusicServiceImp implements Musicservice, ApplicationListener<ContextRefreshedEvent> , CommandLineRunner {

    @Value("${muzix.1.name}")
    String name1;
    @Value("${muzix.1.comment}")
    String comment1;
    @Value("${muzix.2.name}")
    String name2;
    @Value("${muzix.2.comment}")
    String comment2;


    @Autowired
    public MusicServiceImp(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    private MuzixRepository muzixRepository;

    //Method to save track
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {

        if(muzixRepository.existsById(track.getTrackId())){
            throw new TrackAlreadyExistsException("Track Already Exists");
        }
        Track savedTrack=muzixRepository.save(track);
        if(savedTrack == null){
            throw new TrackAlreadyExistsException("null values not allowed");
        }
        return savedTrack;
    }

    //method to delete track
    public int deleteTrack(int trackId) throws TrackNotFoundException {
        Track track=new Track();
        if(!muzixRepository.findById(trackId).isPresent()){
            throw new TrackNotFoundException("id not found");
        }
        muzixRepository.deleteById(trackId);
        return trackId;
    }

    //method to getAllTracks
    public List<Track> getAllTracks() {

        return muzixRepository.findAll();
    }

    public Optional<Track> getTrackById(int id){

       return muzixRepository.findById(id);
    }

    //method to UpdateTrack which is already present
    public Track UpdateTrack(Track track,int trackId) throws TrackNotFoundException {
        if (muzixRepository.existsById(track.getTrackId())) {
            Track updatedTrack= muzixRepository.save(track);
            return updatedTrack;

        } else {
            throw new TrackNotFoundException("Track you are searching is not found!!!");
        }
    }

    //Method to get the Track by name
    public List<Track> getByName(String trackName){

        List<Track> tracks = muzixRepository.findTitleByName(trackName);
        return tracks;
    }

    //Overriding ContextRefreshedEvent
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        muzixRepository.save(new Track(1,name1,comment1));
        muzixRepository.save(new Track(2,name2,comment2));

    }

    //Overriding CommandLineRunner
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner Implemented.");
    }
}
