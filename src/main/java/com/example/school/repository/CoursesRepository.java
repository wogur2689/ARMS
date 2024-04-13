package com.example.school.repository;

import com.example.school.domain.Courses;
import com.example.school.dto.CoursesJpaResultDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {
    Optional<List<Courses>> findByLectureDateAndSemester(String lectureDate, int semester);

    @Query("SELECT SUM(grades) FROM Courses")
    int countByGrades();

    @Query("SELECT NEW com.example.school.dto.CoursesJpaResultDto(c.lectureDate, c.semester, SUM(c.grades)) FROM Courses c WHERE c.semester = :semester GROUP BY c.lectureDate, c.semester")
    Optional<CoursesJpaResultDto> getData(@Param("semester") int semester);
}
