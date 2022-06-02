package com.example.mobilezone_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Slider")
public class Slider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SliderId")
    private Long SliderId;

    @Column(name = "SliderImg")
    private String sliderImg;

    @Column(name = "DisplayNo")
    private Long displayNo;

    @Column(name = "Status")
    private Boolean status;

}
