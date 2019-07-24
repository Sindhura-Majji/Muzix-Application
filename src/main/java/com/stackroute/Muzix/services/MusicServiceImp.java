package com.stackroute.Muzix.services;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//This class will implement MusicService and overRide the methods
@Service
public class MusicServiceImp implements Musicservice {

    @Autowired
    public MusicServiceImp(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    MuzixRepository muzixRepository;

    //Method to save track
    public boolean saveTrack(Track track){
        Track savedTrack=muzixRepository.save(track);
        return true;
    }

    //method to delete track
    public boolean deleteTrack(int trackId){
        muzixRepository.deleteById(trackId);
        return true;

    }

    //method to getAllTracks
    public List<Track> getAllTracks(){
        return muzixRepository.findAll();
    }

    public Optional<Track> getTrackById(int id){
       return muzixRepository.findById(id);

    }

    //method to UpdateTrack which is already present
    public boolean UpdateTrack(Track track,int trackId){
        Optional<Track> userOptional = muzixRepository.findById(trackId);
        if (!userOptional.isPresent())
            return false;
        muzixRepository.save(track);
        return true;
    }

    //Method to get the Track by name
    public List<Track> getByName(String trackName){
        List<Track> tracks = muzixRepository.findTitleByName(trackName);
        return tracks;
    }
}
