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

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackRepositoryTest {


    @Autowired
    TrackRepository trackRepository;

    Track track;

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
    }


    @Test
    public void testSaveUser() {
        Track fetchUser = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(101, fetchUser.getId());
    }

    @Test
    public void testSaveUserFailure() {
        Track testUser = new Track(10,"John","Jenny");
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testUser, track);
    }

    @Test
    public void testGetAllUser() {
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


}
