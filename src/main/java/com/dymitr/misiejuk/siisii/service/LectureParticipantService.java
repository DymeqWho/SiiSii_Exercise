package com.dymitr.misiejuk.siisii.service;

import com.dymitr.misiejuk.siisii.lecture.dao.LectureEntity;
import com.dymitr.misiejuk.siisii.participant.dao.ParticipantEntity;
import com.dymitr.misiejuk.siisii.repository.LectureRepository;
import com.dymitr.misiejuk.siisii.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class LectureParticipantService {
    private final ParticipantRepository participantRepository;
    private final LectureRepository lectureRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public LectureEntity goToLecture(Long lectureId, Long participantId) {
        ParticipantEntity participantEntity = participantRepository.findById(participantId).orElseThrow(() -> new RuntimeException("No such ID"));
        LectureEntity lectureEntity = lectureRepository.findById(lectureId).orElseThrow(() -> new RuntimeException("No such ID"));
        try {
            participantEntity = participantRepository.findById(participantId).get();
            lectureEntity = lectureRepository.findById(lectureId).get();
        } catch (NoSuchElementException e) {
            logger.warn(e.getMessage());
        }

        long freeLectureCapacity = lectureEntity.getMax_capacity();
        int howManyLecturesParticipantIsSignedIn = participantEntity.getLectureEntitySet().size();

        if (freeLectureCapacity > 0
                && howManyLecturesParticipantIsSignedIn < 3
                || participantEntity.getLectureEntitySet().isEmpty()
        ) {
            lectureEntity.getParticipantEntities().add(participantEntity);

//          ZBUGOWANE!!!!
//            if (isParticipantAlreadySignedToLecture(lectureId, participantId, participantEntity.getParticipantName()))
//                lectureRepository.findById(lectureId).orElseThrow().setMax_capacity(lectureRepository.findById(lectureId).orElseThrow().getMax_capacity() - 1);
            LocalDateTime localDateTime = LocalDateTime.now();
            String emailText = "Reservation " + localDateTime + " to " + participantEntity.getParticipantName() + ", email: " + participantEntity.getEmail() + lectureEntity;

            sendEmailAsAMockTxt(emailText);

            return lectureRepository.save(lectureEntity);
        } else if (howManyLecturesParticipantIsSignedIn > 3) {
            logger.info(participantEntity.getParticipantName() + " zapisał się już na 3 lekcje!");
        }
        return lectureEntity;
    }

//    private static void sendEmailAsAMockTxt(String emailContent) throws IOException {
//        Files.write(Paths.get("com/dymitr/misiejuk/siisii/service/sended_Email2.txt"), emailContent.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
//    }

    private void sendEmailAsAMockTxt(String emailContent){
        try{
            FileWriter fileWriter = new FileWriter("sended_Email2.txt");
            fileWriter.write(emailContent);
            fileWriter.close();
        }catch (IOException e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }


//    private boolean isParticipantAlreadySignedToLecture(long lectureId, long participantID) {
//        List<LectureEntity> lectureEntityList = lectureRepository.findAll();
//        List<ParticipantEntity> participantEntityList = participantRepository.findAll();

//        for (int i = 0; i < lectureEntityList.size(); i++) {
//            if (participantRepository.findById(participantID).orElseThrow().getLectureEntitySet().contains(lectureEntityList.get(i))) {
//                logger.warn(participantName + " is already signed to lecture number: " + lectureId);
//                lectureRepository.findById(lectureId).orElseThrow().setMax_capacity(lectureRepository.findById(lectureId).orElseThrow().getMax_capacity() - 1);
//                return true;
//            }
//        }
//        lectureRepository.findById(lectureId).orElseThrow().setMax_capacity(lectureRepository.findById(lectureId).orElseThrow().getMax_capacity() - 1);
//        return false;
//    }
//
//    private boolean isParticipantAlreadySignedToLecture(long lectureId, long participantID, String participantName) {
//        List<LectureEntity> lectureEntityList = lectureRepository.findAll();
//
//        for (int i = 1; i < lectureEntityList.size(); i++) {
//            if (participantRepository.findById(participantID).orElseThrow().getLectureEntitySet().contains(lectureEntityList.get(i))) {
//                logger.warn(participantName + " is already signed to lecture number: " + lectureId);
//                return true;
//            }
//            lectureRepository.findById(lectureId).orElseThrow().setMax_capacity(lectureRepository.findById(lectureId).orElseThrow().getMax_capacity() - 1);

//        }
//        return false;
//    }
}
