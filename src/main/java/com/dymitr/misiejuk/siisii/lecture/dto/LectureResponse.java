package com.dymitr.misiejuk.siisii.lecture.dto;

import com.dymitr.misiejuk.siisii.participant.dao.ParticipantEntity;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureResponse {
    private String title;
    private String duration;
    private Long max_capacity;
    private Set<ParticipantEntity> participantEntities;
}
