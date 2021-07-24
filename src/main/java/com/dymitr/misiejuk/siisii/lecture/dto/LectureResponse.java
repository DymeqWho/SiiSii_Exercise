package com.dymitr.misiejuk.siisii.lecture.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureResponse {
    private String title;
    private String duration;
    private Long max_capacity;
}
