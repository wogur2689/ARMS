package com.example.school.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class PageController {

    /**
     * 메인
     */
    @GetMapping("/")
    public ModelAndView mainPage(ModelAndView mav) {
        mav.setViewName("index");
        return mav;
    }

    /**
     * 로그인
     */
    @GetMapping("/login")
    public ModelAndView loginPage(ModelAndView mav) {
        mav.setViewName("login");
        return mav;
    }

    /**
     * 학년별 이수 학점 조회
     */
    @GetMapping("/credit")
    public ModelAndView creditInquiryPage(ModelAndView mav) {
        mav.setViewName("credit");
        return mav;
    }

    /**
     * 수강 신청하기
     */
    @GetMapping("/enrolment")
    public ModelAndView enrolmentPage(ModelAndView mav) {
        mav.setViewName("enrolment");
        return mav;
    }

    /**
     * 수강 신청 조회
     */
    @GetMapping("/enrolmentInquiry")
    public ModelAndView enrolmentInquiryPage(ModelAndView mav) {
        mav.setViewName("enrolmentInquiry");
        return mav;
    }
}
