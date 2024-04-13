package com.example.school.dto;

import com.example.school.domain.Courses;
import lombok.*;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoursesResponseDto {
    private Long id;
    private String lectureDate;    //수강년도
    private int semester;       //학기
    private String subjectCode;  //교과코드
    private String subjectName;  //교과목명
    private String subjectClassification;  //교과구분
    private String professor;    //담당교수
    private int grades;

    //entity -> dto
    public static CoursesResponseDto toDto(Courses courses) {
        return CoursesResponseDto.builder()
                .id(courses.getId())
                .lectureDate(courses.getLectureDate())
                .semester(courses.getSemester())
                .subjectCode(courses.getSubjectCode())
                .subjectName(courses.getSubjectName())
                .subjectClassification(courses.getSubjectClassification())
                .professor(courses.getProfessor())
                .grades(courses.getGrades())
                .build();
    }
}
