package com.stackroute.trackservice.controller;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.TrackNotAvailable;
import com.stackroute.trackservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.trackservice.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController //this tells the dispatcher servlet that this class is an rest controller class
@RequestMapping(value = "api/v1/")//this a class level mapping for http requests
public class TrackController {

    @Autowired
//    @Qualifier("default")
    private TrackService trackService;

    public TrackController() {
    }

    /**
     * @param trackService this is an object reference of TrackService class and we are telling to the
     *                     *                        spring to provide the object of TrackService object using @autowired annotation
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
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        System.out.println(track);
        Track track1 = null;
        track1 = trackService.saveTrack(track);
        responseEntity = new ResponseEntity<Track>(track1, HttpStatus.CREATED);
        return responseEntity;
    }

    /**
     * @param id we have to get the track based on thid id
     * @return track with the given id is returned
     */
    @GetMapping("track/{id}")//this for get request
    public ResponseEntity<?> getTrack(@PathVariable int id) throws TrackNotAvailable {
        Track track1;
        ResponseEntity responseEntity;
//        try{
        track1 = trackService.getTrackById(id);
        responseEntity = new ResponseEntity(track1, HttpStatus.CONFLICT);

//        }
//        catch (TrackNotAvailable tx){
//            responseEntity=new ResponseEntity<>(tx.getMessage(),HttpStatus.NOT_FOUND);
//            tx.printStackTrace();
//        }

        return responseEntity;

    }

    /**
     * @return a list of tracks is returned
     */
    @GetMapping("track")
    public ResponseEntity<?> getAllTrack() {
        ResponseEntity responseEntity;
//        try {
        List<Track> list = new ArrayList<>();
        list = trackService.getAllTracks();
        responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
//        }catch (Exception ex)
//        {
//            responseEntity=new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//            ex.printStackTrace();
//        }

        return responseEntity;

    }

    /**
     * @param id bassed on the given id the track will be deleted
     * @return//returning the string message based on the deletion
     */
    @DeleteMapping("track/{id}")//this is used for delete request
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) throws TrackNotAvailable {
        ResponseEntity responseEntity;
//        try{
        String str = trackService.deleteTrackById(id);
        responseEntity = new ResponseEntity(str, HttpStatus.OK);
//        }catch(TrackNotAvailable tx){
//            responseEntity=new ResponseEntity(tx.getMessage(),HttpStatus.NOT_FOUND);
//        }
        return responseEntity;
    }

    /**
     * @param id               based on the id value the track will be slected for updating
     * @param trackToBeUpdated this the name of the track which will be upadted
     * @return updated tracl
     */
    @PutMapping("track/{id}")
    public ResponseEntity<?> updateById(@PathVariable int id, @RequestBody Track trackToBeUpdated) throws TrackNotAvailable {
        ResponseEntity responseEntity;
//        try{
        Track track = trackService.updateTrack(id, trackToBeUpdated);
        responseEntity = new ResponseEntity(track, HttpStatus.OK);
//        }catch (TrackNotAvailable tx){
//            responseEntity=new ResponseEntity(tx.getMessage(),HttpStatus.NOT_FOUND);
//        }

        return responseEntity;
    }

    @GetMapping("tracks/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) throws TrackNotAvailable {
        List<Track> track = trackService.getByName(name);
        return new ResponseEntity<>(track, HttpStatus.OK);
    }


}