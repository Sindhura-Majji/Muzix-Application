package com.stackroute.Muzix.repository;

import com.stackroute.Muzix.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MuzixRepositoryTest {

    @Autowired
    MuzixRepository muzixRepository;
    Track track;

    List<Track> list = null;


    @Before
    public void setUp() {
        track = new Track();
        track.setTrackId(1);
        track.setTrackName("sindhu");
        track.setTrackComment("good");
        list = new ArrayList<>();
        list.add(track);

    }

    @After
    public void tearDown() {

        muzixRepository.deleteAll();
    }

    @Test
    public void testSaveTrack() {
        muzixRepository.save(track);
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(1, fetchTrack.getTrackId());

    }

    @Test
    public void testGetAllTracks() {
        muzixRepository.save(track);
        List<Track> fetchTrack = muzixRepository.findAll();
        Assert.assertEquals(list, fetchTrack);
    }

    @Test
    public void testUpdateTrack() {
        // save one object in database
        muzixRepository.save(track);
        // fetch that object from database
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        //update any field of the track
        track.setTrackComment("bad");
        // save it again
        muzixRepository.save(track);
        // fetch it again and verify that updated field is there or not .
        Assert.assertEquals("bad", fetchTrack.getTrackComment());
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeleteTrack() {
        // save one object in database
        muzixRepository.save(track);
        // fetch that object from database
        Track fetchTrack = muzixRepository.findById(track.getTrackId()).get();
        // verify fetched data is not null
        Assert.assertEquals("sindhu",fetchTrack.getTrackName());
        // delete the data
        muzixRepository.deleteById(1);
        // fetch again and verify its null now
//        Assert.assertEquals(null,muzixRepository.findById(track.getTrackId()).get());
        muzixRepository.findById(track.getTrackId()).get();
    }
}