package com.dymitr.misiejuk.siisii.participant.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantResponse {

    private String participantName;
    private String email;

}
