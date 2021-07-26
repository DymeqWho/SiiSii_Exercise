package com.dymitr.misiejuk.siisii.controller;

import com.dymitr.misiejuk.siisii.participant.dto.ParticipantAllResponse;
import com.dymitr.misiejuk.siisii.participant.dto.ParticipantRequest;
import com.dymitr.misiejuk.siisii.participant.dto.ParticipantResponse;
import com.dymitr.misiejuk.siisii.participant.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
public class ParticipantController {

    private final ParticipantService participantService;

    @PostMapping(path = "/api/participant")
    public void setNewParticipant(@RequestBody ParticipantRequest participantRequest) {
        participantService.createParticipant(participantRequest);
    }

    @GetMapping(path = "/api/participant/{participantId}")
    public ParticipantResponse showParticipant(@PathVariable(name = "participantId") Long id) {
        return participantService.showParticipant(id);
    }

    @GetMapping(path = "/api/participant/login/{participantLogin}")
    public ParticipantResponse showParticipantByLogin(@PathVariable(name = "participantLogin") String participantLogin) {
        return participantService.showParticipant(participantLogin);
    }

    @PutMapping(path = "/api/participant/login/{participantLogin}/update_email")
    public void updateEmail(@RequestBody ParticipantRequest participantRequest, @PathVariable(name = "participantLogin") String participantLogin) {
        participantService.updateEmail(participantLogin, participantRequest);
    }

    @GetMapping(path = "/api/all_participants")
    public ParticipantAllResponse showAllParticipants() {
        return participantService.showAllParticipants();
    }

}
