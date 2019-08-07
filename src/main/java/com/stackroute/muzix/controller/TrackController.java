package com.stackroute.muzix.controller;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.services.Musicservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")

public class TrackController {

    Musicservice musicservice;

    @Autowired
    public TrackController(Musicservice musicservice) {
        this.musicservice = musicservice;
    }

    //saveTrack
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try{
            musicservice.saveTrack(track);
            responseEntity=new ResponseEntity<String>("successfully Created", HttpStatus.CREATED);
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Get all the tracks in the database
    @GetMapping("track")
    public ResponseEntity<?> getAllTracks()
    {
        return new ResponseEntity<List<Track>>(musicservice.getAllTracks(),HttpStatus.OK);
    }

    //Delete the track based on trackId
    @DeleteMapping("delete/{trackId}")
    public ResponseEntity<?> deleteTrack(@PathVariable int trackId){
        ResponseEntity responseEntity;
        try{
            musicservice.deleteTrack(trackId);
            responseEntity=new ResponseEntity<String>("successfully deleted", HttpStatus.OK);
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //update the track based on trackId
    @PutMapping("update/{trackId}")
    public ResponseEntity<?> updateTrack(@RequestBody Track track,@PathVariable int trackId){
        ResponseEntity responseEntity;
        try{
            musicservice.UpdateTrack(track,trackId);
            responseEntity =new ResponseEntity<String>("successfully updated", HttpStatus.OK);
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

}
