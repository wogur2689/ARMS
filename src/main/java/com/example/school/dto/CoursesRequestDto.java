package com.example.school.dto;

import com.example.school.domain.Courses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoursesRequestDto {
    private Long id;
    private Date lectureDate;    //수강년도
    private int semester;       //학기
    private String subjectCode;  //교과코드
    private String subjectName;  //교과목명
    private String subjectClassification;  //교과구분
    private String professor;    //담당교수
    private int grades;

    //dto -> entity
    public Courses toEntity(CoursesRequestDto coursesRequestDto) {
        return Courses.builder()
                .lectureDate(coursesRequestDto.lectureDate)
                .semester(coursesRequestDto.getSemester())
                .subjectCode(coursesRequestDto.getSubjectCode())
                .subjectName(coursesRequestDto.getSubjectName())
                .subjectClassification(coursesRequestDto.getSubjectClassification())
                .professor(coursesRequestDto.getProfessor())
                .grades(coursesRequestDto.getGrades())
                .build();
    }
}
