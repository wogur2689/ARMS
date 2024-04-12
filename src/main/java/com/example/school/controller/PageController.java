package com.example.school.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class PageController {

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
    public ModelAndView creditInquiryPage(ModelAndView mav) {
        mav.setViewName("credit");
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
