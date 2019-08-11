package com.stackroute.trackservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.trackservice.domain.Track;
import com.stackroute.trackservice.exceptions.GlobalException;
import com.stackroute.trackservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.trackservice.exceptions.TrackNotAvailableException;
import com.stackroute.trackservice.service.TrackService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Track track;
    @MockBean
    private TrackService trackService;
    @InjectMocks
    private TrackController trackController;

    private List<Track> list = null;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController).setControllerAdvice(new GlobalException()).build();
        track = new Track();

        track.setTrack("song");
        track.setId(101);
        track.setComments("Jenny");

        list = new ArrayList();

        list.add(track);
    }

    @After
    public void tearDown() {
        list=null;
        track = null;
    }


    @Test
    public void givenMethodWillSaveTheTrack() throws Exception {
        when(trackService.saveTrack(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService, times(1)).saveTrack(track);

    }

    @Test
    public void givenMethodWillTestForSaveTrackFailure() throws Exception {
        when(trackService.saveTrack((Track) any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track/")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService, times(1)).saveTrack(track);

    }

    @Test
    public void givenMethodShouldReturnAllTracks() throws Exception {
        when(trackService.getAllTracks()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService, times(1)).getAllTracks();
    }


    @Test
    public void givenMethodShouldUpdateTrack() throws Exception {
        Track track1 = new Track();
        track1.setTrack("fgdff");
        track1.setId(101);
        track1.setComments("ssjs");
        when(trackService.updateTrack(101, track1)).thenReturn(track1);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
                  verify(trackService, times(1)).updateTrack(11,track1);
    }


    @Test
    public void givenMethodShouldDeleteTheTrack() throws Exception {

        when(trackService.deleteTrackById(101)).thenReturn("deleted");
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
                verify(trackService, times(1)).deleteTrackById(anyInt());
    }


    @Test
    public void givenIdShouldReturnTrackNotAvailableException() throws TrackNotAvailableException , Exception{
        when(trackService.deleteTrackById(anyInt())).thenThrow(TrackNotAvailableException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/111")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
             verify(trackService, times(1)).deleteTrackById(anyInt());

    }

    @Test
    public void givenUrlWithNameShouldReturnServerException() throws TrackNotAvailableException, Exception {
        when(trackService.getByName(any())).thenThrow(Exception.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/yedho onnu solluvom")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andDo(MockMvcResultHandlers.print());
        verify(trackService, times(1)).getByName(anyString());

    }

    @Test
    public void givenIdAndTrackShouldReturnTheServerException() throws TrackNotAvailableException, Exception {
        when(trackService.updateTrack(anyInt(),any())).thenThrow(TrackNotAvailableException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/track/1414")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
                verify(trackService, times(1)).updateTrack(anyInt(),any());

    }

    @Test
    public void givenIdShouldReturnException() throws Exception{
        when(trackService.deleteTrackById(5656)).thenThrow(TrackNotAvailableException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/111")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
                verify(trackService, times(1)).deleteTrackById(anyInt());

    }


        private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

