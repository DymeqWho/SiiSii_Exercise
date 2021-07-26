package com.dymitr.misiejuk.siisii.repository;

import com.dymitr.misiejuk.siisii.participant.dao.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
    @Query("SELECT p.id FROM ParticipantEntity p WHERE p.participantName= ?1")
    Long findIDByParticipantName(String participantName);
}
