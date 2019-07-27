package com.stackroute.Muzix.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.services.Musicservice;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TrackControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Musicservice musicservice;

    Track track;

    @InjectMocks
    TrackController trackController;

    List<Track> list= null;

    @Before
    public void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(trackController).build();

        track = new Track();
        track.setTrackId(1);
        track.setTrackName("sindhu");
        track.setTrackComment("good");
        list = new ArrayList<>();
        list.add(track);

        track = new Track(1,"sindhu","good");
    }

    @Test
    public void testTrackControllerSave() throws Exception{
        when(musicservice.saveTrack(track)).thenReturn(true);
        mockMvc.perform(post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isCreated())
                .andDo(print());

        verify(musicservice,times(1)).saveTrack(Mockito.any(Track.class));
        verifyNoMoreInteractions(musicservice);
    }

    @Test
    public void testTrackControllerGetAll() throws Exception{
        when(musicservice.getAllTracks()).thenReturn(list);
        mockMvc.perform(get("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(musicservice,times(1)).getAllTracks();
        verifyNoMoreInteractions(musicservice);
    }

    @Test
    public void testTrackControllerDelete() throws Exception{
        when(musicservice.deleteTrack(1)).thenReturn(true);
        mockMvc.perform(delete("/api/v1/delete/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON).content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(musicservice,times(1)).deleteTrack(anyInt());

    }


    @Test
    public void testTrackControllerUpdate() throws Exception{
        when(musicservice.UpdateTrack(track,1)).thenReturn(true);
        mockMvc.perform(put("/api/v1/update/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonToString(track)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(musicservice,times(1)).UpdateTrack(track,1);
        verifyNoMoreInteractions(musicservice);
    }



    public String jsonToString(final Track track)
    {
        String string="";
        try {
            string=new ObjectMapper().writeValueAsString(track);
        } catch( JsonProcessingException e) {
            e.printStackTrace();
        }
        return string;
    }

}
