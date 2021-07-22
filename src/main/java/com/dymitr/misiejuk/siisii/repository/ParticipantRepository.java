package com.dymitr.misiejuk.siisii.repository;

import com.dymitr.misiejuk.siisii.participant.dao.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
}
