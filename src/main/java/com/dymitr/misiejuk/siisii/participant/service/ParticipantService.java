package com.dymitr.misiejuk.siisii.participant.service;

import com.dymitr.misiejuk.siisii.participant.dao.ParticipantEntity;
import com.dymitr.misiejuk.siisii.participant.dto.ParticipantAllResponse;
import com.dymitr.misiejuk.siisii.participant.dto.ParticipantRequest;
import com.dymitr.misiejuk.siisii.participant.dto.ParticipantResponse;
import com.dymitr.misiejuk.siisii.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public void createParticipant(ParticipantRequest participantRequest) {
        ParticipantEntity participantEntity = new ParticipantEntity();

        if (isEmailRepeat(participantRequest))
            settingParticipantEntity(participantRequest, participantEntity);
        else throw new RuntimeException("Podany login już jest zajęty");

    }

    public ParticipantResponse showParticipant(Long id) {
        ParticipantResponse participantResponse = new ParticipantResponse();
        participantResponse.setParticipantName(participantRepository.findById(id).orElseThrow().getParticipantName());
        participantResponse.setEmail(participantRepository.findById(id).orElseThrow().getEmail());
        participantResponse.setLectureEntitySet(participantRepository.findById(id).orElseThrow().getLectureEntitySet());
        logger.info("Udało się odczytać " + participantResponse.getParticipantName() + " email: "
                + participantResponse.getEmail() + "lista: " + participantResponse.getLectureEntitySet().toString());
        return participantResponse;
    }

    public ParticipantResponse showParticipant(String login) {
        return showParticipant(findParticipantByLogin(login));
    }

    public void updateEmail(String login, ParticipantRequest participantRequest) {
        Long id = findParticipantByLogin(login);
        ParticipantEntity participantEntity = participantRepository.findById(id).orElseThrow();
        participantRequest.setParticipantName(participantEntity.getParticipantName());
        settingParticipantEntity(participantRequest, participantEntity);
    }

    public ParticipantAllResponse showAllParticipants() {
        return ParticipantAllResponse.builder()
                .participantResponseList(participantRepository.findAll().stream()
                        .map(participantEntity -> ParticipantResponse.builder()
                                .participantName(participantEntity.getParticipantName())
                                .email(participantEntity.getEmail())
                                .build())
                        .collect(Collectors.toList())).build();
    }

    public Long findParticipantByLogin(String login) {
        return participantRepository.findIDByParticipantName(login);
    }

    private void settingParticipantEntity(ParticipantRequest participantRequest, ParticipantEntity participantEntity
    ) {
        participantEntity.setEmail(participantRequest.getEmail());
        participantEntity.setParticipantName(participantRequest.getParticipantName());

        participantEntity.setLectureEntitySet(participantEntity.getLectureEntitySet());

        participantRepository.save(participantEntity);
        logger.info("Udało się stworzyć " + participantEntity.getParticipantName() + " email: " + participantEntity.getEmail());
    }

    private boolean isEmailRepeat(ParticipantRequest participantRequest) {
        List<String> emails = new ArrayList<>();
        List<String> logins = new ArrayList<>();
        String email = "";
        String login = "";

        for (int i = 0; i < participantRepository.count(); i++) {
            String id = i + 1 + "";
            ParticipantEntity participantEntityDemo = participantRepository.findById((long) i + 1).orElseThrow(() -> new RuntimeException("there is no such ID " + id));
            email = participantEntityDemo.getEmail();
            login = participantEntityDemo.getParticipantName();
            emails.add(i, email);
            logins.add(i, login);
        }
        logger.info("logins: " + logins + "\n" + "emails: " + emails);

        for (int i = 0; i < emails.size(); i++) {
            if (emails.get(i).equals(participantRequest.getEmail())) {
                logger.warn("takie same emaile" + email);
                return false;
            }
            if (logins.get(i).equals(participantRequest.getParticipantName())) {
                logger.warn("takie same loginy" + login);
                return false;
            }
        }
        return true;
    }

}
