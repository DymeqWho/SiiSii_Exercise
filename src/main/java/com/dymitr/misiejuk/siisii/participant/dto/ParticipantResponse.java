package com.dymitr.misiejuk.siisii.participant.dto;

import com.dymitr.misiejuk.siisii.lecture.dao.LectureEntity;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantResponse {

    private String participantName;
    private String email;
    private Set<LectureEntity> lectureEntitySet;

}
