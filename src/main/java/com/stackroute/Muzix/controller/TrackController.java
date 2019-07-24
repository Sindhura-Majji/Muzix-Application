package com.stackroute.Muzix.controller;

import com.stackroute.Muzix.domain.Track;
import com.stackroute.Muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzix.exceptions.TrackNotFoundException;
import com.stackroute.Muzix.services.Musicservice;
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

    //Post mapping to save the track
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try{
            musicservice.saveTrack(track);
            responseEntity=new ResponseEntity<String>("successfully Created", HttpStatus.CREATED);
        }
        catch (TrackAlreadyExistsException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Get mapping to get all the tracks
    @GetMapping("track")
    public ResponseEntity<?> getAllTracks() {

         return new ResponseEntity<List<Track>>(musicservice.getAllTracks(), HttpStatus.OK);

    }

    //Delete mapping to delete the track
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

    //Put Mapping to update the existing track
    @PutMapping("update/{trackId}")
    public ResponseEntity<?> updateTrack(@RequestBody Track track,@PathVariable int trackId){
        ResponseEntity responseEntity;
        try{
            musicservice.UpdateTrack(track,trackId);
            responseEntity =new ResponseEntity<String>("successfully updated", HttpStatus.OK);
        }
        catch (TrackNotFoundException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Get Mapping to get the Track by name
    @GetMapping("names/{trackName}")
    public ResponseEntity<List<Track>> getByName(@PathVariable String trackName) {

        return new ResponseEntity<List<Track>>(musicservice.getByName(trackName), HttpStatus.OK);

    }

}
