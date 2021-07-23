package com.dymitr.misiejuk.siisii.participant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantRequest {

    private String participantName;
    private String email;

}
