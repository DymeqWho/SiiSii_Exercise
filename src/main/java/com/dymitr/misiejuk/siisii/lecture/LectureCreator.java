package com.dymitr.misiejuk.siisii.lecture;

import java.util.ArrayList;
import java.util.List;

public class LectureCreator {
    private List<Lecture> lectureList = new ArrayList<>();

    public List<Lecture> getLectureList() {
        lectureList.add(new Lecture(1, "First-One", "10:00 - 11:45",5));
        lectureList.add(new Lecture(2, "First-Two", "10:00 - 11:45",5));
        lectureList.add(new Lecture(3, "First-Three", "10:00 - 11:45",5));

        lectureList.add(new Lecture(4, "Second-One", "12:00 - 13:45",5));
        lectureList.add(new Lecture(5, "Second-Two", "12:00 - 13:45",5));
        lectureList.add(new Lecture(6, "Second-Three", "12:00 - 13:45",5));

        lectureList.add(new Lecture(7, "Three-One", "14:00 - 15:45",5));
        lectureList.add(new Lecture(8, "Three-Two", "14:00 - 15:45",5));
        lectureList.add(new Lecture(9, "Three-Three", "14:00 - 15:45",5));
        return lectureList;
    }
}
