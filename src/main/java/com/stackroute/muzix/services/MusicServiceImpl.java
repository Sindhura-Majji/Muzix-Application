package com.stackroute.muzix.services;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicServiceImpl implements Musicservice {

    @Autowired
    public MusicServiceImpl(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    MuzixRepository muzixRepository;

    //Saves the track
    public Track saveTrack(Track track){
        Track savedTrack=muzixRepository.save(track);
        return savedTrack;
    }

    //Delete the track based on trackId
    public int deleteTrack(int trackId){
        muzixRepository.deleteById(trackId);
        return trackId;
    }

    //Gets all the track saved in the database
    public List<Track> getAllTracks(){
        return muzixRepository.findAll();
    }

    //Gets the specified track based on the trackId
    public Optional<Track> getTrackById(int id){
       return muzixRepository.findById(id);
    }

    //Updates the track based on trackId
    public Track UpdateTrack(Track track,int trackId){
        Optional<Track> userOptional = muzixRepository.findById(trackId);
        if (!userOptional.isPresent())
            return track;
        muzixRepository.save(track);
        return track;
    }
}
