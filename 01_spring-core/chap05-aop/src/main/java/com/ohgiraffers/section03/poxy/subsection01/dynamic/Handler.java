package com.ohgiraffers.section03.poxy.subsection01.dynamic;

import com.ohgiraffers.section03.poxy.common.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* 호출에 따른 프록시에서 처리할 메소드를 구현하기 위해 InvocationHandler를 구현할 클래스를 작성 */
public class Handler implements InvocationHandler {

    /* 메소드 호출을 위해 타켓 인스턴스가 필요 */
    private final Student student;

    public Handler(Student student) { this.student = student; }
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