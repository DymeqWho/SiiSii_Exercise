package com.dymitr.misiejuk.siisii.repository;

import com.dymitr.misiejuk.siisii.participant.dao.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
}
