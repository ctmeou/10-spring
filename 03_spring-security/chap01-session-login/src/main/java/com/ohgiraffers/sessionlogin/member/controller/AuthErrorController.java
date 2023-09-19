package com.ohgiraffers.sessionlogin.member.controller;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/error")
public class AuthErrorController {

    private final MessageSourceAccessor messageSourceAccessor;

    public AuthErrorController(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @PostMapping("/login")
    public String loginFailed(RedirectAttributes rttr) {
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.login"));
                                            //문자열 직접 작성하지 않고 분리된 파일에 있는 것을 꺼내옴
        //로그인 페이지로 다시 가게끔, 그냥 가면 어떤 문제인지 모르기 때문에 alret 창 뜨게 함
        return "redirect:/member/login";
    }

    @GetMapping("/denied")
    public String accessDenied(RedirectAttributes rttr) {
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.denied"));
        return "redirect:/";
    }

}
