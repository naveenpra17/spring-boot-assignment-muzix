package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.ErrorWithConnectingToTheDataBase;
import com.stackroute.trackservice.exceptions.TrackNotAvailable;
import com.stackroute.trackservice.exceptions.UserAlreadyExistsException;


import java.util.List;
import java.util.Optional;

/**
 * this is an interface of service class which has service methods
 */
public interface TrackService {
    public Track saveTrack(Track track) throws UserAlreadyExistsException, ErrorWithConnectingToTheDataBase;
    public Track getTrackById(int id) throws TrackNotAvailable;
    public List<Track> getAllTracks();
    public String deleteTrackById(int id) throws TrackNotAvailable;
    public Track updateTrack(int id,Track trackToBeUpdated) throws TrackNotAvailable;

}
