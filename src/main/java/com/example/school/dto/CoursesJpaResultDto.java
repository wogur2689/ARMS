package com.example.school.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoursesJpaResultDto {
    private String lectureDate;    //수강년도
    private int semester;       //학기
    private Long grades;
}
