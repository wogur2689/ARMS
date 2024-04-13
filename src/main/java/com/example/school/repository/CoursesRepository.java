package com.example.school.repository;

import com.example.school.domain.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {
    Optional<List<Courses>> findByLectureDateAndSemester(String lectureDate, int semester);

    @Query("SELECT SUM(grades) FROM Courses")
    int countByGrades();
}
