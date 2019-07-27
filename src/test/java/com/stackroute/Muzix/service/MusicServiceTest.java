package com.stackroute.Muzix.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzix.exceptions.TrackNotFoundException;
import com.stackroute.Muzix.repository.MuzixRepository;
import com.stackroute.Muzix.services.MusicServiceImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MusicServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MuzixRepository muzixRepository;

    @InjectMocks
    private MusicServiceImp musicServiceImp;
    List<Track> list= null;

    Track track;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(musicServiceImp).build();

        track = new Track();
        track.setTrackId(1);
        track.setTrackName("sindhu");
        track.setTrackComment("good");
        list = new ArrayList<>();
        list.add(track);
     //   track = new Track(1,"sindhu","good");

    }
    @Test
    public void testSaveTrackService() throws Exception{
        when(muzixRepository.save(track)).thenReturn(track);
        boolean savedTrack=musicServiceImp.saveTrack(track);

        Assert.assertEquals(true,savedTrack);

        verify(muzixRepository, Mockito.times(1)).save(track);
    }

    @Test
    public void testGetAllTracks() throws Exception{
        muzixRepository.save(track);
        when(muzixRepository.findAll()).thenReturn(list);
        List<Track> trackList = musicServiceImp.getAllTracks();
        Assert.assertEquals(list,trackList);
    }

    @Test
    public void testDeleteTrackService() throws Exception{
        when(muzixRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(track));
        doNothing().when(muzixRepository).deleteById(anyInt());
//        doThrow(EmptyStackException.class).when(muzixRepository).delete(any());
        musicServiceImp.deleteTrack(1);
        verify(muzixRepository, times(1)).findById(anyInt());
        verify(muzixRepository, times(1)).deleteById(anyInt());
    }
    @Test
    public void testUpdateTrackService() throws TrackNotFoundException{
        when(muzixRepository.existsById(anyInt())).thenReturn(true);
        boolean updateTrack= musicServiceImp.UpdateTrack(track,1);

        Assert.assertEquals(true,updateTrack);

        verify(muzixRepository, Mockito.times(1)).save(track);
        verify(muzixRepository, Mockito.times(1)).existsById(anyInt());

    }
}
