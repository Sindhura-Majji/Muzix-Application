package com.stackroute.Muzix.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Track is a pojo class
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Track {



    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private int trackId;
    private String trackName;
    private String trackComment;


}
