package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.TrackNotAvailable;
import com.stackroute.trackservice.exceptions.TrackAlreadyExistsException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Qualifier("default")
@Profile("mainservice")
public class TrackDummyServiceImpl implements TrackService {
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        return null;
    }

    @Override
    public Track getTrackById(int id) throws TrackNotAvailable {
        return null;
    }

    @Override
    public List<Track> getAllTracks() {
        return null;
    }

    @Override
    public String deleteTrackById(int id) throws TrackNotAvailable {
        return null;
    }

    @Override
    public Track updateTrack(int id, Track trackToBeUpdated) throws TrackNotAvailable {
        return null;
    }

    @Override
    public List<Track> getByName(String name) throws TrackNotAvailable {
        return null;
    }
}
