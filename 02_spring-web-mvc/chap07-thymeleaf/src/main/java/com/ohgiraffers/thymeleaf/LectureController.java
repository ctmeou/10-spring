package com.ohgiraffers.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/lecture")
public class LectureController {

    @GetMapping("/expression")
    public ModelAndView expression(ModelAndView mv) {

        mv.addObject("member", new MemberDTO("황주희", 20, '여', "서울시 강북구"));

        mv.addObject("hello", "hello!<h3>Thymeleaf</h3>");

        mv.setViewName("lecture/expression");

        return mv;

    }

    @GetMapping("/conditional")
    public ModelAndView conditional(ModelAndView mv) {

        mv.addObject("num", 1);
        mv.addObject("str", "복숭아");

        mv.setViewName("lecture/conditional");

        return mv;

    }

}
