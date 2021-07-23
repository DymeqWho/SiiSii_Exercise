package com.dymitr.misiejuk.siisii.participant.service;

import com.dymitr.misiejuk.siisii.participant.dao.ParticipantEntity;
import com.dymitr.misiejuk.siisii.participant.dto.ParticipantRequest;
import com.dymitr.misiejuk.siisii.participant.dto.ParticipantResponse;
import com.dymitr.misiejuk.siisii.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public void createParticipant(ParticipantRequest participantRequest) {
        ParticipantEntity participantEntity = new ParticipantEntity();
        settingParticipantEntity(participantRequest, participantEntity);
    }

    public ParticipantResponse showParticipant(Long id) {
        ParticipantResponse participantResponse = new ParticipantResponse();
        participantResponse.setParticipantName(participantRepository.findById(id).orElseThrow().getParticipantName());
        participantResponse.setEmail(participantRepository.findById(id).orElseThrow().getEmail());
        logger.info("Udało się odczytać " + participantResponse.getParticipantName() + " email: " + participantResponse.getEmail());
        return participantResponse;
    }



    private void settingParticipantEntity(ParticipantRequest participantRequest,
                                          ParticipantEntity participantEntity
    ) {
        participantEntity.setEmail(participantRequest.getEmail());
        participantEntity.setParticipantName(participantRequest.getParticipantName());
            participantRepository.save(participantEntity);
            logger.info("Udało się stworzyć " + participantEntity.getParticipantName() + " email: " + participantEntity.getEmail());
    }

}
