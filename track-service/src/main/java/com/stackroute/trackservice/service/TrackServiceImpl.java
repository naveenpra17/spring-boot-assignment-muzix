package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * this tells the dispatcher servlet that this class is a service class which has buisness logics
 */
@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    /**
     * @param trackRepository this is an object reference of TrackRepository class and we are telling to the
     *                        spring to provide the object of TrackRepository object using @autowired annotation
     */
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }


    /**
     * @param track this track is given by track controller by calling saveTrack method from TrackController
     * @return this track1 is the Track object returned by trackrepository.save()
     */
    @Override
    public Track saveTrack(Track track) {
          Track track1=trackRepository.save(track);
          return track1;
    }

    /**
     * @param id this id is passed by the Testcontroller class
     * @return this is a track object which has the track of the given id
     */
    @Override
    public Track getTrackById(int id) {
        Track track=trackRepository.findById(id).get();
        return track;
    }

    /**
     * @return the list obj contains the list of tracks returned by trackrepository class
     */
    @Override
    public List<Track> getAllTracks() {
        List<Track> list=trackRepository.findAll();
        return list;
    }

    /**
     * @param id this id is passed by TrackController for deleting the particular track which has the id value same as given id value
     * @return this returns a string message about the deleted track
     */
    @Override
    public String deleteTrackById(int id) {
       Optional<?> optional= trackRepository.findById(id);
       String s=" ";
       if(optional.isPresent()){
           trackRepository.deleteById(id);
           s="deleted"+optional.toString();
       }
       else
           return "id is invalid";
        return s;
    }

    /**
     * @param id ths is the id value for which we have to update the track
     * @param trackToBeUpdated the new track which will be updated
     * @return new track is updated
     */
    @Override
    public Track updateTrack(int id,Track trackToBeUpdated) {
        Track track=trackRepository.getOne(id);
        track.setTrack(trackToBeUpdated.getTrack());
        track.setComments(trackToBeUpdated.getComments());
        System.out.println(track);

        return trackRepository.save(track);
    }
}
