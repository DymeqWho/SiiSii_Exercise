package com.dymitr.misiejuk.siisii.repository;

import com.dymitr.misiejuk.siisii.lecture.dao.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<LectureEntity, Long> {
}
