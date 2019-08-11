package com.stackroute.trackservice.service;

import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.ErrorWithConnectingToTheDataBase;
import com.stackroute.trackservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.trackservice.exceptions.TrackNotAvailableException;
import com.stackroute.trackservice.repository.TrackRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//import com.stackroute.domain.User;
//import com.stackroute.exception.UserAlreadyExistException;
//import com.stackroute.repository.UserRepository;

public class TrackServiceImplTest {


    private Track track;

    //Create a mock for UserRepository
    @Mock
    private TrackRepository trackRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private TrackServiceImpl trackService;
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

    @After
    public void tearDown() {
        track = null;
    }
	//test case for returning saved track
    @Test
    public void givenMethodShouldReturnTheSavedTrack() throws TrackNotAvailableException, TrackAlreadyExistsException, ErrorWithConnectingToTheDataBase {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedUser = trackService.saveTrack(track);
        Assert.assertEquals(track, savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository, times(1)).save(track);

    }

//test case for track already exits
    @Test(expected = TrackAlreadyExistsException.class)
    public void givenMethodShouldTestForSaveTrackFailure() throws TrackAlreadyExistsException {
        trackRepository.save(track);
        System.out.println(track);
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        Track savedUser = trackService.saveTrack(track);
        verify(trackRepository, times(1)).save(any());


    }

    //test case for returning all tarcks
    @Test
    public void givenMethodShouldReturnAllTracks() {

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> userlist = trackService.getAllTracks();
        Assert.assertEquals(list, userlist);
        verify(trackRepository, times(1)).findAll();

    }
		//test case for method which should return tack based on given id
    @Test
    public void givenMethodShouldReturnTrackById() throws TrackNotAvailableException {

        when(trackRepository.findById(101)).thenReturn((Optional.of(track)));

        Track track1 = trackService.getTrackById(101);
        Assert.assertEquals(track, track1);
        verify(trackRepository, times(1)).findById(anyInt());

    }

    @Test
    public void givenMethodShoulDeleteTrackById() throws TrackNotAvailableException {
        System.out.println(track);
        when(trackRepository.findById(101)).thenReturn((Optional.of(track)));
        String deleteTrack = trackService.deleteTrackById(101);
        System.out.println(deleteTrack);

        Assert.assertEquals("deletedOptional" + "[" + track + "]", deleteTrack.toString());
        verify(trackRepository, times(1)).findById(anyInt());

    }

    //test case for updating track
    @Test
    public void givenIdShouldReturnUpdatedTrack() throws TrackNotAvailableException, Exception {
        Track track1 = new Track();
        track1.setId(11);
        track1.setComments("dog");
        track1.setTrack("seeme");
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        when(trackRepository.findById(track.getId())).thenReturn(Optional.of(track));
        Track savedTrack = trackService.updateTrack(101, track1);
        Assert.assertEquals(track1, savedTrack);
        verify(trackRepository, times(1)).findById(anyInt());

    }
	//test case for server error
    @Test(expected = Exception.class)
    public void shouldReturnAnServerException() throws TrackAlreadyExistsException, Exception {
        when(trackRepository.findAll()).thenThrow(Exception.class);
        trackService.saveTrack(track);
        verify(trackRepository, times(1)).findAll();
    }

    @Test(expected = Exception.class)
    public void givenTrackNameshouldReturnServerException() throws TrackAlreadyExistsException, Exception {
        when(trackRepository.findByTrack(anyString())).thenThrow(Exception.class);
        trackService.updateTrack(anyInt(), any());
        verify(trackRepository, times(1)).findByTrack(any());
    }
	//track not available
    @Test(expected = TrackNotAvailableException.class)
    public void givenNameShouldReturnException() throws TrackNotAvailableException, Exception {
        trackService.getByName("akoo");
        verify(trackRepository, times(1)).findByTrack(any());
    }
	//track update failure test case
    @Test(expected = Exception.class)
    public void givenTrackIdshouldReturnTheServerException() throws TrackAlreadyExistsException, Exception {
        when(trackRepository.existsById(anyInt())).thenReturn(true);
        when(trackRepository.findById(any())).thenThrow(Exception.class);
        trackService.updateTrack(anyInt(), any());
        verify(trackRepository, times(1)).findById(anyInt());
    }
}