package com.stackroute.Muzix.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;

//Track is a pojo class
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PropertySource("com.stackroute.resource")
public class Track {



    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private int trackId;
    private String trackName;
    private String trackComment;


}
