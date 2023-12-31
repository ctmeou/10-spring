package com.ohgiraffers.sessionlogin.member.controller;

import com.ohgiraffers.sessionlogin.member.dto.CustomUser;
import com.ohgiraffers.sessionlogin.member.dto.MemberDTO;
import com.ohgiraffers.sessionlogin.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    //의존성 주입 반영
    private final MemberService memberService;
    private final MessageSourceAccessor messageSourceAccessor;

    public MemberController(MemberService memberService, MessageSourceAccessor messageSourceAccessor) {
        this.memberService = memberService;
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @GetMapping("/login")
    public void loginPage() {}

    @GetMapping("/mypage")
    public void mypage(@AuthenticationPrincipal CustomUser user) {
        //void이라고 작성해서 실제 mypage.html 연결
        log.info("로그인한 유저의 번호 : {}", user.getNo());
        log.info("로그인한 유저의 이름 : {}", user.getName());
        log.info("로그인한 유저의 등록일 : {}", user.getRegistDate());
    }

    @GetMapping("/join")
    public void joinPage(){}

    @PostMapping("/join")
    public String memberJoin(@ModelAttribute MemberDTO member, RedirectAttributes rttr) {

        memberService.joinMember(member);
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.join"));

        return "redirect:/member/login";

    }
}
