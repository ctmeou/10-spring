package com.ohgiraffers.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleStringController {

    @GetMapping("/simple-string")
    public String showSimpleString() {

        return "async/simple-string";

    }

    @ResponseBody
    @GetMapping(value="/xmlhttprequest/simple-string")
    public String xmlhttprequestTest(@RequestParam String keyword) {

        String responseText = "서버로 전달된 문자열은 " + keyword + "입니다.";

        return responseText;

    }

    @ResponseBody
    @GetMapping(value = "/jquery/simple-string")
    public String jqueryTest(@RequestParam String keyword) {

        String responseText = "서버로 전달된 문자열은 " + keyword + "입니다.";

        return responseText;

    }

    @ResponseBody
    @GetMapping(value = "/fetch/simple-string")
    public String fetchTest(@RequestParam String keyword) {

        String responseText = "서버로 전달된 문자열은 " + keyword + "입니다.";

        return responseText;

    }

    @ResponseBody
    @GetMapping(value = "/axios/simple-string")
    public String axiosTest(@RequestParam String keyword) {

        String responseText = "서버로 전달된 문자열은 " + keyword + "입니다.";

        return responseText;

    }
}
