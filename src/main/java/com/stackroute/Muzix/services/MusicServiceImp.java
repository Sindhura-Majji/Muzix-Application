package com.stackroute.Muzix.services;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzix.exceptions.TrackNotFoundException;
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

    private MuzixRepository muzixRepository;

    //Method to save track
    public boolean saveTrack(Track track) throws TrackAlreadyExistsException {

        if(muzixRepository.existsById(track.getTrackId())){
            throw new TrackAlreadyExistsException("Track Already Exists");
        }
        Track savedTrack=muzixRepository.save(track);
        if(savedTrack == null){
            throw new TrackAlreadyExistsException("null values not allowed");
        }
        return true;
    }

    //method to delete track
    public boolean deleteTrack(int trackId) throws TrackNotFoundException {
        Track track=new Track();
        if(!muzixRepository.findById(trackId).isPresent()){
            throw new TrackNotFoundException("id not found");
        }
        muzixRepository.deleteById(trackId);
        return true;
    }

    //method to getAllTracks
    public List<Track> getAllTracks() {

        return muzixRepository.findAll();
    }

    public Optional<Track> getTrackById(int id){

       return muzixRepository.findById(id);
    }

    //method to UpdateTrack which is already present
    public boolean UpdateTrack(Track track,int trackId) throws TrackNotFoundException {
        if (muzixRepository.existsById(track.getTrackId())) {
            Track updatedTrack= muzixRepository.save(track);
            return true;

        } else {
            throw new TrackNotFoundException("Track you are searching is not found!!!");
        }
    }

    //Method to get the Track by name
    public List<Track> getByName(String trackName){

        List<Track> tracks = muzixRepository.findTitleByName(trackName);
        return tracks;
    }

}
