package com.dymitr.misiejuk.siisii.lecture.service;

import com.dymitr.misiejuk.siisii.lecture.dao.LectureEntity;
import com.dymitr.misiejuk.siisii.lecture.dto.LectureResponse;
import com.dymitr.misiejuk.siisii.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LectureService {
    private final LectureRepository lectureRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<LectureEntity> showConferencePlan() {
       return lectureRepository.findAll();
    }
}
