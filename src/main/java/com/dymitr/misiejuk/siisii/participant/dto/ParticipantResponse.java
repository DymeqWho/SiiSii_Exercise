package com.dymitr.misiejuk.siisii.participant.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantResponse {
    private long id;
    private String participantName;
    private String email;
    private List<Integer> idOfLectures;
}
