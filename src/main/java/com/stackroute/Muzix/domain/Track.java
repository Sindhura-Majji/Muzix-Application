package com.stackroute.Muzix.domain;

import javax.persistence.*;

//Track is a pojo class
@Entity
public class Track {

    public Track(int trackId, String trackName, String trackComment){
        this.trackId = trackId;
        this.trackName = trackName;
        this.trackComment = trackComment;
    }

    //Override the toString method
    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", trackName='" + trackName + '\'' +
                ", trackComment='" + trackComment + '\'' +
                '}';
    }

    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private int trackId;
    private String trackName;
    private String trackComment;

    //Setters and Getters
    public Track() {
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackComment() {
        return trackComment;
    }

    public void setTrackComment(String trackComment) {
        this.trackComment = trackComment;
    }


}
