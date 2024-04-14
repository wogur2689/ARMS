package com.example.school.domain;

import com.example.school.dto.CoursesRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * 강의 테이블
 */
@Getter
@Entity
@Table(name = "courses")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "lecture_date")
    private String lectureDate;    //수강년도

    @Column(name = "semester")
    private int semester;       //학기

    @Column(name = "subject_code")
    private String subjectCode;    //교과코드

    @Column(name = "subject_name")
    private String subjectName;    //교과목명

    @Column(name = "subject_classification")
    private String subjectClassification;    //교과구분

    @Column(name = "professor")
    private String professor;    //담당교수

    @Column(name = "grades")
    private int grades;     //학점
}
