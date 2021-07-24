package com.dymitr.misiejuk.siisii.participant.dto;

import com.dymitr.misiejuk.siisii.lecture.dao.LectureEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantRequest {

    private String participantName;
    private String email;
    private Set<LectureEntity> lectureEntitySet;
}
