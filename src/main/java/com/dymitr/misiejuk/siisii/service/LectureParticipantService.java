package com.dymitr.misiejuk.siisii.service;

import com.dymitr.misiejuk.siisii.lecture.dao.LectureEntity;
import com.dymitr.misiejuk.siisii.participant.dao.ParticipantEntity;
import com.dymitr.misiejuk.siisii.participant.dto.ParticipantResponse;
import com.dymitr.misiejuk.siisii.repository.LectureRepository;
import com.dymitr.misiejuk.siisii.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LectureParticipantService {
    private final ParticipantRepository participantRepository;
    private final LectureRepository lectureRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ParticipantResponse goToLecture(Long lectureId, Long participantId) {
        ParticipantEntity participantEntity = participantRepository.findById(participantId).orElseThrow(() -> new RuntimeException("there is no such ID!"));
        LectureEntity lectureEntity = lectureRepository.findById(lectureId).orElseThrow(() -> new RuntimeException("There is no such ID!"));
        ParticipantResponse participantResponse = new ParticipantResponse();
        String name = participantEntity.getParticipantName();
        String email = participantEntity.getEmail();

        int countOfParticipatedLecturesByParticipant = participantEntity.getLectureEntitySet().size();

        if (countOfParticipatedLecturesByParticipant < 4 && lectureEntity.getMax_capacity() > 0) {
            participantEntity.getLectureEntitySet().add(lectureEntity);
            lectureEntity.setMax_capacity(lectureEntity.getMax_capacity() - 1);
            participantRepository.save(participantEntity);
            lectureRepository.save(lectureEntity);
            participantResponse.setParticipantName(name);
            participantResponse.setEmail(email);
            participantResponse.setLectureEntitySet(participantEntity.getLectureEntitySet());
            return participantResponse;

        } else {
            if (countOfParticipatedLecturesByParticipant > 3)
                throw new RuntimeException("nie możesz uczestniczyć w większel liczbie wykładów!");
            if (lectureEntity.getMax_capacity() >= 5)
                throw new RuntimeException("liczba miejsc na ten wykład została wyczerpana!");
        }


        participantResponse.setParticipantName(name);
        participantResponse.setEmail(email);
        logger.warn("Użytkownik " + name + " z emailem " + email + " nie mógł się zapisać na zapodany wykład!");
        return participantResponse;

    }
}
