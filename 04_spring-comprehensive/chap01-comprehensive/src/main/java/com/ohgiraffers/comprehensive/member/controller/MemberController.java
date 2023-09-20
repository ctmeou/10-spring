package com.ohgiraffers.comprehensive.member.controller;

import com.ohgiraffers.comprehensive.common.exception.member.MemberModifyException;
import com.ohgiraffers.comprehensive.common.exception.member.MemberRegistException;
import com.ohgiraffers.comprehensive.common.exception.member.MemberRemoveException;
import com.ohgiraffers.comprehensive.member.dto.MemberDTO;
import com.ohgiraffers.comprehensive.member.service.AuthenticationService;
import com.ohgiraffers.comprehensive.member.service.MemberDeleteException;
import com.ohgiraffers.comprehensive.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationService authenticationService;
    private final MessageSourceAccessor messageSourceAccessor;
    private final PasswordEncoder passwordEncoder;

    public MemberController(MemberService memberService, AuthenticationService authenticationService, MessageSourceAccessor messageSourceAccessor, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.authenticationService =  authenticationService;
        this.messageSourceAccessor = messageSourceAccessor;
        this.passwordEncoder = passwordEncoder;
    }

    /* 로그인 페이지 이동 */
    @GetMapping("/login")
    public void loginPage() {}

    /* 로그인 실패 시 */
    @PostMapping("/loginfail")
    public String loginFailed(RedirectAttributes rttr) {
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.login"));
        return "redirect:/member/login";
    }

    /* 회원 가입 페이지 이동 */
    @GetMapping("/regist")
    public void registPage() {}

    /* 아이디 중복 체크 : 비동기 통신
    * ResponseEntity : 사용자의 HttpRequest에 대한 응답 데이터를 포함하는 클래스
    * (HttpStatus, HttpHeaders, HttpBody를 포함한다.)
    * 규약에 맞는 HttpResponse를 반환하는데 사용 목적이 있다. */
    @PostMapping("/idDupCheck")                 //JSON형태로 보내 RequestBody 붙여줘야 함
    public ResponseEntity<String> checkDuplication(@RequestBody MemberDTO member) {

        log.info("Request Check ID : {}", member.getMemberId());

        String result = "사용 가능한 아이디입니다.";

        if (memberService.selectMemberById(member.getMemberId())) {
            result = "중복된 아이디가 존재합니다.";
        }

        return ResponseEntity.ok(result);

    }

    /* 회원 가입 */
    @PostMapping("/regist") //우편번호, 주소, 상세주소는 MemberDTO에 설정되어 있지 않아 RequestParam으로 받고 생략 가능(어노테이션으로 하는 설정은 못 함)
    public String registMember(MemberDTO member, String zipCode, String address1, String address2,
                               RedirectAttributes rttr) throws MemberRegistException {

        String address =zipCode + "$" + address1 + "$" + address2;
        member.setAddress(address);
        member.setMemberPwd(passwordEncoder.encode(member.getPassword())); //password 가공해서 전달

        log.info("Request regist member : {}", member);

        memberService.registMemebr(member);

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.regist"));

        //회원가입 후 main 페이지로 보냄(redirect 처리, 회원가입이 다시 발생하지 않도록 새로운 곳으로 보냄
        return "redirect:/";

    }

    /* 회원 정보 화면 이동 */
    @GetMapping("/update")
    public void modifyPage(@AuthenticationPrincipal MemberDTO member, Model model) {
                                //AuthenticationPrincipal : 인증객체 참조
        //adress 합쳐놓은 것을 분리
        String[] address = member.getAddress().split("\\$"); //\\두 개룰 작성해야 달러 문자를 인식
        model.addAttribute("address", address);
    }

    /* 회원 정보 수정 */
    @PostMapping("/update")
    public String modifyMember(MemberDTO modifyMember, String zipCode, String address1, String address2,
                               @AuthenticationPrincipal MemberDTO loginMember, RedirectAttributes rttr) throws MemberModifyException {
                                //loginMembef로부터 누구인지 알 수 있음

        String address = zipCode + "$" + address1 + "$" + address2;
        modifyMember.setAddress(address);
        modifyMember.setMemberNo(loginMember.getMemberNo());

        log.info("modifyMember request Member : {}", modifyMember);

        memberService.modifyMember(modifyMember);

        /* 로그인 시 저장된 Authentication 객체를 변경된 정보로 교체한다. */
        SecurityContextHolder.getContext().setAuthentication(createNewAuthcentication(loginMember.getMemberId()));

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.modify"));

        return "redirect:/";

    }

    protected Authentication createNewAuthcentication(String memberId) {

        UserDetails newPrincipal = authenticationService.loadUserByUsername(memberId);
        UsernamePasswordAuthenticationToken newAuth
        = new UsernamePasswordAuthenticationToken(newPrincipal, newPrincipal.getPassword(), newPrincipal.getAuthorities());

        return newAuth;

    }

    /* 회원 탈퇴 */
    /* 로그인된 멤버 정보를 전달하며 memberService.removeMember(member) 메소드 호출
    * SecurityCOntextHolder.clearContext(); => 메소드를 호출하면 outhentication 정보가 지워짐
    * 탈퇴 완료 alert
    * index 화면으로 이동 */
    @GetMapping("/delete")
    public String deleteMember(@AuthenticationPrincipal MemberDTO member, RedirectAttributes rttr) throws MemberRemoveException {

        log.info("login member : {}", member);

        memberService.removeMember(member);

        SecurityContextHolder.clearContext();

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.delete"));

        return "redirect:/";

    }
}
