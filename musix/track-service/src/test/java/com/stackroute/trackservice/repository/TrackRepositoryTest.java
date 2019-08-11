package com.stackroute.trackservice.repository;

import com.stackroute.trackservice.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackRepositoryTest {


    @Autowired
   private TrackRepository trackRepository;

   private  Track track;

    @Before
    public void setUp() {
        track = new Track();
        track.setId(101);
        track.setTrack("John");
        track.setComments("Jenny");
        trackRepository.save(track);
    }

    @After
    public void tearDown() {
        trackRepository.deleteAll();
        track=null;
    }

    //Test for savetrack
    @Test
    public void givenMethodWillTestForSavedTrack() {
        Track fetchUser = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(101, fetchUser.getId());
    }

    @Test
    public void givenMethodWillTestForFailureOfSavingTrack() {
        Track testUser = new Track(10,"John","Jenny");
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testUser, track);
    }
    //        Test for getAllTtracks
    @Test
    public void givenMethodWillGetAllTracks() {
        trackRepository.save(track);
        Track u = new Track(11,"karthi","xyz");
        Track u1 = new Track(12,"naveen","dfdf");
        trackRepository.save(u);
        trackRepository.save(u1);
        List<Track> list = trackRepository.findAll();
        System.out.println(list);
        Assert.assertEquals("karthi", list.get(1).getTrack());
//        Assert.assertEquals("naveen",list.get().getTrack());
    }
    //        Test for deleteTrack
    @Test
    public void GivenTrackIdShouldDeletetrackAndReturnNull() {
        trackRepository.save(track);
        trackRepository.delete(track);
        Optional<Track> expected = Optional.empty();

        Assert.assertEquals(expected, trackRepository.findById(track.getId()));
    }
    //        Test for updatingTrack
    @Test
    public void GivenTrackWithSameIdShouldUpdatedTrackOfThatId(){

        Track trackToUpdate=new Track();
        trackRepository.save(track);
        Track updatedTrack = trackRepository.findById(trackToUpdate.getId()).get();
        updatedTrack.setTrack(trackToUpdate.getTrack());
        updatedTrack.setComments(trackToUpdate.getComments());
        Track expected=trackToUpdate;
        Track foundTrack=trackRepository.save(trackToUpdate);

        Assert.assertEquals(expected,foundTrack);
    }

}
