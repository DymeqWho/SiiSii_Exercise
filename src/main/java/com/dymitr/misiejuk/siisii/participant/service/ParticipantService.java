package com.dymitr.misiejuk.siisii.participant.service;

import com.dymitr.misiejuk.siisii.lecture.Lecture;
import com.dymitr.misiejuk.siisii.lecture.LectureCreator;
import com.dymitr.misiejuk.siisii.participant.dao.ParticipantEntity;
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

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final List<ParticipantEntity> participantEntityList = new ArrayList<>(participantRepository.findAll());
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public void createParticipant(ParticipantRequest participantRequest) {
        ParticipantEntity participantEntity = new ParticipantEntity();
        settingParticipantEntity(participantRequest, participantEntity);
    }

    public void updatingEmail(Long id, ParticipantRequest participantRequest) {
        ParticipantEntity participantEntity = participantRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no such id!"));

        if (emailChecker(participantRequest, participantEntity)) {
            participantEntity.setEmail(participantEntity.getEmail());
        }
    }

    public void updatingListOfParticipantsLectures(Long id, ParticipantResponse participantResponse) {
        int sizeOfIdOfLecturesList = 0;

        if (participantResponse.getIdOfLectures() != null) {
            sizeOfIdOfLecturesList = participantResponse.getIdOfLectures().size();
        } else {
            throw new NullPointerException("The list is empty");
        }

        int idOfLecture = participantResponse.getIdOfLectures().get(sizeOfIdOfLecturesList);
        ParticipantEntity participantEntity = participantRepository.findById(id).orElseThrow(() -> new RuntimeException("There is no such id!"));

        LectureCreator lectureCreator = null;
        Lecture lecture = lectureCreator.getLectureList().get(participantResponse.getIdOfLectures().get(idOfLecture));

        //dodać uaktualnienia ilości osób na danej lekcji "capacity"

        if (idOfLecture >= 1 && idOfLecture <= 3 || idOfLecture >= 4 && idOfLecture <= 6 || idOfLecture >= 7 && idOfLecture <= 9) {
            participantEntity.getIdOfLectures().add(idOfLecture);
        } else {
            logger.error("Już masz rezerwację w tych godzinach! " + lecture.toString());
        }
    }


    private void settingParticipantEntity(ParticipantRequest participantRequest, ParticipantEntity participantEntity) {

        for (ParticipantEntity entity : participantEntityList) {
            if (emailChecker(participantRequest, participantEntity) &&
                    !entity.getParticipantName().equals(participantRequest.getParticipantName())) {
                participantEntity.setEmail(participantRequest.getEmail());
                participantEntity.setParticipantName(participantRequest.getParticipantName());
                participantRepository.save(participantEntity);
            } else {
                logger.info("Podany login jest już zajęty!");
            }
        }
    }

    private boolean emailChecker(ParticipantRequest participantRequest, ParticipantEntity participantEntity) {
        for (ParticipantEntity entity : participantEntityList) {
            if (!entity.getEmail().equals(participantRequest.getEmail())) {
                return true;
            } else {
                logger.info("Taki email już jest zajęty!");
                return false;
            }
        }
        return false;
    }


}
