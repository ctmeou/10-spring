package com.ohgiraffers.section01.xmlconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {

    public static void main(String[] args) {

        /* GenericXmlApplicationContext 클래스를 사용하여 ApplicationContext를 생성한다.
        * 생성자에 XML 설정 메타 정보를 이자로 전달한다. */
        ApplicationContext context = new GenericXmlApplicationContext("section01/xmlconfig/spring-context.xml");

        /* 1. bean의 id를 이용해서 bean을 가져오는 방법 */
        // MemberDTO member = (MemberDTO) context.getBean("member");

        /* 2. bean의 클래스 메타 정보를 전달하여 가져오는 방법 */
        // MemberDTO member = context.getBean(MemberDTO.class);

        /* 3. bean의 id와 클래스 메타 정보를 전달하여 가져오은 방법 */
        MemberDTO member = context.getBean("member", MemberDTO.class);

        System.out.println(member);

    }

}
