package com.dymitr.misiejuk.siisii.lecture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor

public class Lecture {
    private int id;
    private String name;
    private String time;

    @Min(0)
    @Max(5)
    private int capacity;
}
