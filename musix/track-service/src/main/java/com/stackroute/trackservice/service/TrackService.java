package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.TrackNotAvailable;
import com.stackroute.trackservice.exceptions.TrackAlreadyExistsException;


import java.util.List;

/**
 * this is an interface of service class which has service methods
 */
public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;
    public Track getTrackById(int id) throws TrackNotAvailable;
    public List<Track> getAllTracks();
    public String deleteTrackById(int id) throws TrackNotAvailable;
    public Track updateTrack(int id,Track trackToBeUpdated) throws TrackNotAvailable;
    public List<Track> getByName(String name) throws TrackNotAvailable;

}
