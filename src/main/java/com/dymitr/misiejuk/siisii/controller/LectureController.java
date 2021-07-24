package com.dymitr.misiejuk.siisii.controller;

import com.dymitr.misiejuk.siisii.lecture.dao.LectureEntity;
import com.dymitr.misiejuk.siisii.lecture.service.LectureService;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class LectureController {
    private final LectureService lectureService;

    @GetMapping(path = "/api/lectures")
    public List<LectureEntity> showLectures(){
        return lectureService.showConferencePlan();
    }
}
