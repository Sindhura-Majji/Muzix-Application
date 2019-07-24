package com.stackroute.Muzix.services;

import com.stackroute.Muzix.domain.Track;

import java.util.List;
import java.util.Optional;

public interface Musicservice {

    public boolean saveTrack(Track track);

    public boolean deleteTrack(int trackId);

    public List<Track> getAllTracks();

    public Optional<Track> getTrackById(int trackId);

    public boolean UpdateTrack(Track track, int trackId);
}
