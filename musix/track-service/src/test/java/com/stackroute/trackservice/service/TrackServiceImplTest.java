package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.ErrorWithConnectingToTheDataBase;
import com.stackroute.trackservice.exceptions.TrackNotAvailable;
import com.stackroute.trackservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.trackservice.repository.TrackRepository;
import org.junit.Assert;
//import com.stackroute.domain.User;
//import com.stackroute.exception.UserAlreadyExistException;
//import com.stackroute.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceImplTest {


    Track track;

    //Create a mock for UserRepository
    @Mock
    TrackRepository trackRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    TrackServiceImpl trackService;
    List<Track> list = null;


    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();

        track.setId(101);
        track.setTrack("Jenny");
        track.setComments("10");
        list = new ArrayList<>();
        list.add(track);


    }

    @Test
    public void saveUserTestSuccess() throws TrackNotAvailable, TrackAlreadyExistsException, ErrorWithConnectingToTheDataBase {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedUser = trackService.saveTrack(track);
        Assert.assertEquals(track, savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository, times(1)).save(track);

    }


        @Test(expected = TrackAlreadyExistsException.class)
        public void saveUserTestFailure() throws TrackAlreadyExistsException {
            trackRepository.save(track);
            System.out.println(track);
            when(trackRepository.existsById(track.getId())).thenReturn(true);
            Track savedUser = trackService.saveTrack(track);
//            System.out.println(track);
//            System.out.println("savedUser" + savedUser);
//            Assert.assertEquals(track,savedUser);

            /*doThrow(new UserAlreadyExistsException()).when(trackRepository).findById(eq(101));
            trackService.saveTrack(track);*/


            }
//
    @Test
    public void getAllTrack() {

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> userlist = trackService.getAllTracks();
        Assert.assertEquals(list, userlist);
    }

//    @Test
//    public void getTrackById() throws TrackNotAvailable{
//
//        trackRepository.save(track);
//        when(trackRepository.findById(track.getId()).thenReturn(track));
//
//    }
}


