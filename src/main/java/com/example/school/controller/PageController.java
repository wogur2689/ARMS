package com.example.school.controller;

import com.example.school.service.CoursesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

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
        mav.addObject("list", coursesService.coursesList());
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

    @GetMapping("/enrolmentInquiry")
    public ModelAndView enrolmentInquiryPage(ModelAndView mav) {
        mav.setViewName("enrolmentInquiry");
        return mav;
    }
}
