package com.example.school.service;

import com.example.school.domain.Courses;
import com.example.school.dto.CoursesJpaResultDto;
import com.example.school.dto.CoursesRequestDto;
import com.example.school.dto.CoursesResponseDto;
import com.example.school.repository.CoursesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CoursesService {
    private final CoursesRepository coursesRepository;

    //create
    public void coursesSave(CoursesRequestDto coursesRequestDTO) {
        coursesRepository.save(coursesRequestDTO.toEntity(coursesRequestDTO));
    }

    //2024년 2학기 수강신청 내역
    @Transactional(readOnly = true)
    public List<CoursesResponseDto> coursesList() {
        List<Courses> list = coursesRepository.findByLectureDateAndSemester("2024", 2).orElseThrow();

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
            List<CoursesJpaResultDto> o = coursesRepository.getData(i).orElseThrow();

            for(CoursesJpaResultDto c : o) {
                Courses courses = Courses.builder()
                        .lectureDate(c.getLectureDate())
                        .semester(c.getSemester())
                        .grades(c.getGrades().intValue())
                        .build();
                lists.add(CoursesResponseDto.toDto(courses));
            }
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
}
