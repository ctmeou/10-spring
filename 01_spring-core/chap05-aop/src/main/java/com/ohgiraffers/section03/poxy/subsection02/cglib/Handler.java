package com.ohgiraffers.section03.poxy.subsection02.cglib;

import com.ohgiraffers.section03.poxy.common.OhgiraffersStudent;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/* reflect가 아닌 cglib 패키지의 인터페이스를 구현한다. */
public class Handler implements InvocationHandler {

    /* 인터페이스가 아닌 클래스 타입의 Target Object 사용 */
    private final OhgiraffersStudent student;

    public Handler(OhgiraffersStudent student) { this.student = student; }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("========== 너무나도 이해하고 싶어요. =========");
        System.out.println("호출 대상 메소드 : " + method);
        for (Object arg : args) {
            System.out.println("전달된 인자 : " + arg);
        }

        /* 타겟 메소드를 호출한다. 타겟 obj와 인자를 매개변수로 전달한다. */
        method.invoke(student, args);


        System.out.println("========== 이해하는 날이 오겠죠... =========");

        return proxy;

    }
}