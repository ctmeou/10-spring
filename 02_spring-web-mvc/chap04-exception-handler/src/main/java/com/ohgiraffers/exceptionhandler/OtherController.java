package com.ohgiraffers.exceptionhandler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {

    @GetMapping("/other-controller-null")
    public String nullPointExceptonTest() {

        String str = null;
        System.out.println(str.charAt(0));      // 의도적으로 NullPointerException 발생 시킴

        return "/";

    }

    @GetMapping("/other-controller-user")
    public String userExceptionTest() {

        boolean check = true;
        if (check) try {
            throw new MemberRegistException("당신 같은 사람은 회원으로 받을 수 없습니다.");
        } catch (MemberRegistException e) {
            throw new RuntimeException(e);
        }

        return "/";

    }

    @GetMapping("/other-controller-array")
    public String otherArrayExceptionTest() {

        //배열을 생성, 크기가 0이어서 접근 자체가 불가
        double[] array = new double[0];
        System.out.println(array[0]);

        return "/";

    }

}
