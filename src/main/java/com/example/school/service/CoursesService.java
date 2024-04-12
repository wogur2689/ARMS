package com.example.school.service;

import com.example.school.domain.Courses;
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

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CoursesService {
    private final CoursesRepository coursesRepository;
    private static final int size = 5; //한 페이지당 보여질 게시글 갯수

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

    //view
    @Transactional(readOnly = true)
    public CoursesResponseDto coursesView(Long id) {
        Courses courses = coursesRepository.findById(id).orElseThrow();
        return CoursesResponseDto.toDto(courses);
    }

    //update
    public CoursesResponseDto coursesUpdate(CoursesRequestDto coursesRequestDTO) {
        Courses courses = coursesRepository.findById(coursesRequestDTO.getId()).orElseThrow();
        //변경감지
        courses.coursesUpdate(coursesRequestDTO);
        return CoursesResponseDto.toDto(courses);
    }
}
