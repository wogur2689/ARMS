package com.example.school.dto;

import com.example.school.domain.Courses;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoursesRequestDto {
    @NotBlank(message = "수강년도를 입력하세요.")
    private String lectureDate;    //수강년도

    @NotNull
    @Min(value = 1, message = "1 ~ 2학기만 입력 가능합니다.")
    @Max(value = 2, message = "1 ~ 2학기만 입력 가능합니다.")
    private int semester;       //학기

    @NotBlank(message = "교과코드를 입력하세요.")
    private String subjectCode;  //교과코드

    @NotBlank(message = "교과목명을 입력하세요.")
    private String subjectName;  //교과목명

    @NotBlank(message = "교과구분을 입력하세요.")
    private String subjectClassification;  //교과구분

    @NotBlank(message = "교수님 성함을 입력하세요.")
    private String professor;    //담당교수
    @NotNull
    @Min(value = 1, message = "1 ~ 5학점만 입력 가능합니다.")
    @Max(value = 5, message = "1 ~ 5힉점만 입력 가능합니다.")
    private int grades;          //학점

    //dto -> entity
    public Courses toEntity(CoursesRequestDto coursesRequestDto) {
        return Courses.builder()
                .lectureDate(coursesRequestDto.getLectureDate())
                .semester(coursesRequestDto.getSemester())
                .subjectCode(coursesRequestDto.getSubjectCode())
                .subjectName(coursesRequestDto.getSubjectName())
                .subjectClassification(coursesRequestDto.getSubjectClassification())
                .professor(coursesRequestDto.getProfessor())
                .grades(coursesRequestDto.getGrades())
                .build();
    }
}
