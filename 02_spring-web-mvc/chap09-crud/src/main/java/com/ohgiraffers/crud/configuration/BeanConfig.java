package com.ohgiraffers.crud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;

import java.util.Locale;

@Controller //Bean scanning될 수 있도록
public class BeanConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {

        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:/messages/message");
        //어떤 파일을 기반으로 동작할 건지 뒤의 국가 코드나 언어 코드는 생략
        source.setDefaultEncoding("UTF-8");
        /* 제공하지 않는 언어로 요청 시 MessageSource에서 사용할 default 언어로 한국 설정 */
        Locale.setDefault(Locale.KOREA);

        return source;

    }
}
