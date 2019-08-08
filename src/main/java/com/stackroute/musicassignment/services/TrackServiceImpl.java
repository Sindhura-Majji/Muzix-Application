package com.stackroute.musicassignment.services;

import com.stackroute.musicassignment.domain.Track;
import com.stackroute.musicassignment.exceptions.TrackAlreadyExistsException;
import com.stackroute.musicassignment.exceptions.TrackNotFoundException;
import com.stackroute.musicassignment.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService{

    TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository)
    {
        this.trackRepository = trackRepository;
    }

    //save the track
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {

        if(trackRepository.existsById(track.getId()))
        {
            throw new TrackAlreadyExistsException("Track already exists");
        }
        Track savedTrack = trackRepository.save(track);

        if(savedTrack == null)
        {
            throw new TrackAlreadyExistsException("Track already exists");
        }
        return savedTrack;
    }

    //get all the tracks in the database
    @Override
    public List<Track> getAllTracks() {

        return trackRepository.findAll();
    }

    //get the particular tracl based on the name
    @Override
    public List<Track> getTracksByName(String name) {

        return trackRepository.getTrackByName(name);

    }

    //updates the track based on Id
    public Track updateTrack(Track track, int id) throws TrackNotFoundException
    {
        Optional<Track> track1 = trackRepository.findById(id);

        if(!track1.isPresent())
        {
            throw new TrackNotFoundException("Track Not Found");
        }

        track.setId(id);

        Track savedTrack = trackRepository.save(track);
        return savedTrack;
    }

    //Deletes the track based on Id
    public boolean deleteTrack(int id) throws TrackNotFoundException
    {
        Optional<Track> track1 = trackRepository.findById(id);

        if(!track1.isPresent())
        {
            throw new TrackNotFoundException("Track Not Found");
        }

        try {

            trackRepository.delete(track1.get());

            return true;

        }
        catch (Exception exception)
        {
            return false;
        }
    }

    //search the track from database
    @Override
    public List<Track> searchTracks(String searchString) {

        return trackRepository.searchTracks(searchString);
    }
}
