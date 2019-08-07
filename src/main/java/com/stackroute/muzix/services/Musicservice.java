package com.stackroute.muzix.services;

import com.stackroute.muzix.domain.Track;

import java.util.List;
import java.util.Optional;

public interface Musicservice {

    public Track saveTrack(Track track);

    public int deleteTrack(int trackId);

    public List<Track> getAllTracks();

    public Optional<Track> getTrackById(int trackId);

    public Track UpdateTrack(Track track, int trackId);

    public List<Track> getByName(String name);
}
