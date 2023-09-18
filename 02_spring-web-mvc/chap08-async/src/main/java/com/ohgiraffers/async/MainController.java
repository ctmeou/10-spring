package com.ohgiraffers.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value={"/", "/main"})
    public String defaultLocation() { return "main/main"; }
    //MainController에서 루트 요청 또는 main 요청이 오면 main/main으로 리턴하겠다는 것은 templates 아래 main/main 만들어 작업하면 알맞게 forwarding됨

}
