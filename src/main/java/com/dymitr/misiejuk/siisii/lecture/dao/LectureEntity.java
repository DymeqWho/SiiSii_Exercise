package com.dymitr.misiejuk.siisii.lecture.dao;

import com.dymitr.misiejuk.siisii.participant.dao.ParticipantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Lecture")
public class LectureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String duration;

    private Long max_capacity;

    private Long min_capacity;

    @ManyToMany
    @JoinTable(
            name = "participant_enrolled",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private Set<ParticipantEntity> participantEntities = new HashSet<>();

}