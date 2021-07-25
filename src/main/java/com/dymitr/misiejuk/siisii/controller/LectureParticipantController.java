package com.dymitr.misiejuk.siisii.controller;

import com.dymitr.misiejuk.siisii.lecture.dao.LectureEntity;
import com.dymitr.misiejuk.siisii.participant.dao.ParticipantEntity;
import com.dymitr.misiejuk.siisii.participant.dto.ParticipantResponse;
import com.dymitr.misiejuk.siisii.repository.ParticipantRepository;
import com.dymitr.misiejuk.siisii.service.LectureParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Validated
public class LectureParticipantController {
    private final LectureParticipantService lectureParticipantService;

    @PutMapping("/api/{participantID}/lecture/{lectureID}")
    public LectureEntity showEnrolledLectures(@PathVariable Long participantID, @PathVariable Long lectureID) throws IOException {
        return lectureParticipantService.goToLecture(lectureID, participantID);
    }
}
