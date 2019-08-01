package com.stackroute.trackservice.controller;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController //this tells the dispatcher servlet that this class is an rest controller class
@RequestMapping("api/v1/")//this a class level mapping for http requests
public class TrackController {

    private TrackService trackService;

    public TrackController() {
    }

    /**
     * @param trackService this is an object reference of TrackService class and we are telling to the
     *      *                        spring to provide the object of TrackService object using @autowired annotation
     */
    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    /**
     * @param track this will take the body from the http request
     * @return returns the track with status
     */
    @PostMapping("track")//this is used for posting data //this a method level mapping
    public ResponseEntity<?> saveTrack(@RequestBody Track track) {
        Track track1 = trackService.saveTrack(track);
        return new ResponseEntity<>(track1, HttpStatus.OK);
    }

    /**
     * @param id we have to get the track based on thid id
     * @return track with the given id is returned
     */
    @GetMapping("track/{id}")//this for get request
    public ResponseEntity<?> getTrack(@PathVariable int id) {
        Track track1 = trackService.getTrackById(id);
        return new ResponseEntity<>(track1, HttpStatus.OK);

    }

    /**
     * @return a list of tracks is returned
     */
    @GetMapping("track")
    public ResponseEntity<?> getAllTrack() {
        List<Track> list = new ArrayList<>();
        list = trackService.getAllTracks();
        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    /**
     * @param id bassed on the given id the track will be deleted
     * @return//returning the string message based on the deletion
     */
    @DeleteMapping("track/{id}")//this is used for delete request
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        System.out.println("khsdgusasgyd");
        String str = trackService.deleteTrackById(id);
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    /**
     * @param id based on the id value the track will be slected for updating
     * @param trackToBeUpdated this the name of the track which will be upadted
     * @return updated tracl
     */
    @PutMapping("track/{id}")
    public ResponseEntity<?> updateById(@PathVariable int id,@RequestBody Track trackToBeUpdated) {
        Track track = trackService.updateTrack(id,trackToBeUpdated);
        return new ResponseEntity<>(track, HttpStatus.OK);
    }
}