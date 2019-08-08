package com.stackroute.Muzix.services;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzix.exceptions.TrackNotFoundException;

import java.util.List;
import java.util.Optional;

//interface for MusicService
public interface Musicservice {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public int deleteTrack(int trackId) throws TrackNotFoundException;

    public List<Track> getAllTracks();

    public Optional<Track> getTrackById(int trackId) ;

    public Track UpdateTrack(Track track, int trackId) throws TrackNotFoundException;

    public List<Track> getByName(String name);
}
