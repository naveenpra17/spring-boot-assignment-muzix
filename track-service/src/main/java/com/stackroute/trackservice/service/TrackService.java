package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import org.apache.catalina.User;

import java.util.List;
import java.util.Optional;

/**
 * this is an interface of service class which has service methods
 */
public interface TrackService {
    public Track saveTrack(Track track);
    public Track getTrackById(int id);
    public List<Track> getAllTracks();
    public String deleteTrackById(int id);
    public Track updateTrack(int id,Track trackToBeUpdated);

}
