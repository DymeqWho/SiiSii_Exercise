package com.dymitr.misiejuk.siisii.participant.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ParticipantAllResponse {

    private final List<ParticipantResponse> participantResponseList;
}
