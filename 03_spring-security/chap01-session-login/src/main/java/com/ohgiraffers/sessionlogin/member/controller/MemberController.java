package com.ohgiraffers.sessionlogin.member.controller;

import com.ohgiraffers.sessionlogin.member.dto.CustomUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public void loginPage() {}

    @GetMapping("/mypage")
    public void mypage(@AuthenticationPrincipal CustomUser user) {
        //void이라고 작성해서 실제 mypage.html 연결
        log.info("로그인한 유저의 번호 : {}", user.getNo());
        log.info("로그인한 유저의 이름 : {}", user.getName());
        log.info("로그인한 유저의 등록일 : {}", user.getRegistDate());
    }

}
