package com.example.school.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * ·Î±×ÀÎ
     */
    @GetMapping
    public ModelAndView loginPage(ModelAndView mav) {
        mav.setViewName("login/login");
        return mav;
    }
}
