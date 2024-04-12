package com.example.school.controller;

import com.example.school.service.CoursesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CoursesController {
    private final CoursesService coursesService;


    /**
     * 게시판 작성
     */
    @GetMapping("/write")
    public ModelAndView coursesWrite(ModelAndView mav) {
        mav.setViewName("courses/write");
        return mav;
    }

    /**
     * 게시판 수정
     */
    @GetMapping("/edit/{id}")
    public ModelAndView coursesEdit(@PathVariable Long id, ModelAndView mav) {
        mav.addObject("data", coursesService.coursesView(id));
        mav.setViewName("courses/edit");
        return mav;
    }

    /**
     * 게시글 보기
     */
    @GetMapping("/view/{id}")
    public ModelAndView coursesView(@PathVariable Long id, ModelAndView mav) {
        mav.addObject("data", coursesService.coursesView(id));
        mav.setViewName("courses/view");
        return mav;
    }
}
