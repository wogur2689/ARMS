package com.example.school.service;

import com.example.school.domain.Courses;
import com.example.school.dto.CoursesJpaResultDto;
import com.example.school.dto.CoursesRequestDto;
import com.example.school.dto.CoursesResponseDto;
import com.example.school.repository.CoursesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CoursesService {
    private final CoursesRepository coursesRepository;

    //create
    public CoursesResponseDto coursesSave(CoursesRequestDto coursesRequestDTO) {
        Courses courses = coursesRepository.save(coursesRequestDTO.toEntity(coursesRequestDTO));
        return CoursesResponseDto.toDto(courses);
    }

    //list
    @Transactional(readOnly = true)
    public List<CoursesResponseDto> coursesList() {
        List<Courses> list = coursesRepository.findAll();

        return list.stream()
                .map(CoursesResponseDto::toDto)
                .toList();
    }

    //grades sum
    @Transactional(readOnly = true)
    public int sumGrades() {
        return coursesRepository.countByGrades();
    }

    //grades sum where semester
    @Transactional(readOnly = true)
    public List<CoursesResponseDto> sumGradesWhereSemester() {
        List<CoursesResponseDto> lists = new ArrayList<>();
        for(int i = 1; i < 3; i++) {
            CoursesJpaResultDto o = coursesRepository.getData(i).orElseThrow();
            Courses courses = Courses.builder()
                    .lectureDate(o.getLectureDate())
                    .semester(o.getSemester())
                    .grades(o.getGrades().intValue())
                    .build();
            lists.add(CoursesResponseDto.toDto(courses));
        }
        return lists;
    }

    //view
    @Transactional(readOnly = true)
    public List<CoursesResponseDto> coursesView(String year, int semester) {
        List<Courses> coursesList = coursesRepository.findByLectureDateAndSemester(year, semester).orElseThrow();

        return coursesList.stream()
                .map(CoursesResponseDto::toDto)
                .toList();
    }

    //update
    public CoursesResponseDto coursesUpdate(CoursesRequestDto coursesRequestDTO) {
        Courses courses = coursesRepository.findById(coursesRequestDTO.getId()).orElseThrow();
        //변경감지
        courses.coursesUpdate(coursesRequestDTO);
        return CoursesResponseDto.toDto(courses);
    }
}
