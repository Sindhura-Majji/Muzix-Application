package com.stackroute.muzix.domain;

import javax.persistence.*;

@Entity
public class Track {

    //Parameterised constructor
    public Track(int trackId, String trackName, String trackComment){
        this.trackId = trackId;
        this.trackName = trackName;
        this.trackComment = trackComment;
    }

    //Overriding toString
    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", trackName='" + trackName + '\'' +
                ", trackComment='" + trackComment + '\'' +
                '}';
    }

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int trackId;
    private String trackName;
    private String trackComment;

    //Zero param constructor
    public Track() {
    }

    //setters and getters
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
