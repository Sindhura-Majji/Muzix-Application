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

    //save track for saving the track
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

    //get all tracks to get the tracks from database
    @Override
    public List<Track> getAllTracks() {

        return trackRepository.findAll();
    }

    //updates the track based on trackId
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

    //Delets the track based on trackId
    public int deleteTrack(int id) throws TrackNotFoundException
    {
        Optional<Track> track1 = trackRepository.findById(id);

        if(!track1.isPresent())
        {
            throw new TrackNotFoundException("Track Not Found");
        }

        try {

            trackRepository.delete(track1.get());

            return id;

        }
        catch (Exception exception)
        {
            return 0;
        }
    }

}
