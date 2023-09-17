package com.ohgiraffers.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class JsonDataController {

     @GetMapping("/json-data")
    public String showJsonData() {

        return "async/json-data";

    }

    /* @RequestBody : 요청 바디에 담긴 데이터를 java 객체에 매핑할 때 사용하는 어노테이션
    * Spring boot web에는 Jackson-databind 라이브러리가 기본 포함되어 JSON => Java Object parsing이 자동으로 처리된다.
    * 1. String //문자열 그 자체로 각각의 데이터로 쓰기 힘듦
    * {"nickname":"홍길동","age":"20","email":"hong@ohgiraffers.com","registDate":"2023-01-01"}
     * 2. Map //Json 형태의 문자열이 알아서 Passing 처리되어 출력됨
    * {nickname=홍길동, age=20, email=hong@ohgiraffers.com, registDate=2023-01-01}
    * 3. UserDTO //UserDTO 자바의 객체 형태로 JSON 문자열이 Passing 처리되어 출력됨
    * UserDTO={id='null', nickname='홍길동', age=20, email='hong@ohgiraffers.com', registDate=Sun Jan 01 09:00:00 KST 2023}*/
    @PostMapping("/jquery/json")
    public @ResponseBody String jqueryJsonTest(@RequestBody UserDTO requestBody) {
        //어노테이션 위치가 위에 있어도 되지만 반환 값에 있어도 됨

        System.out.println("requestBody : " + requestBody);

        return "success";

    }
}
