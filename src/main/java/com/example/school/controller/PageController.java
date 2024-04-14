package com.example.school.controller;

import com.example.school.dto.CoursesRequestDto;
import com.example.school.service.CoursesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PageController {
    private final CoursesService coursesService;

    @GetMapping("/")
    public ModelAndView mainPage(ModelAndView mav) {
        mav.setViewName("index");
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView loginPage(ModelAndView mav) {
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("/credit")
    public ModelAndView creditPage(ModelAndView mav) {
        mav.addObject("list", coursesService.sumGradesWhereSemester());
        mav.addObject("sumGrades", coursesService.sumGrades());
        mav.setViewName("credit");
        return mav;
    }
    @GetMapping("/credit/{year}/{id}")
    public ModelAndView creditInquiryPage(@PathVariable String year, @PathVariable int id,ModelAndView mav) {
        mav.addObject("list", coursesService.coursesView(year, id));
        mav.setViewName("creditDetail");
        return mav;
    }

    @GetMapping("/enrolment")
    public ModelAndView enrolmentPage(ModelAndView mav) {
        mav.setViewName("enrolment");
        return mav;
    }
    @PostMapping(value = "/enrolmentInsert")
    public ResponseEntity<Map<String, Object>> enrolment(@Valid @RequestBody CoursesRequestDto coursesRequestDto, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            log.error("error  : " + result.getFieldError().getDefaultMessage());
            response.put("msg", result.getFieldError().getDefaultMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        coursesService.coursesSave(coursesRequestDto);
        response.put("msg", "성공");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/enrolmentInquiry")
    public ModelAndView enrolmentInquiryPage(ModelAndView mav) {
        mav.addObject("list", coursesService.coursesList());
        mav.setViewName("enrolmentInquiry");
        return mav;
    }
}
