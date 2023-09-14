package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Interceptor를 구현하기 위해서 HandlerInterceptor를 상속받는다. */
@Component
public class StopWatchInterceptor implements HandlerInterceptor {

    /* Interceptor를 구현하기 위해서 HandlerInterceptor를 상속 받는다. */
    private final MenuService menuService;

    public StopWatchInterceptor(MenuService menuService) {

        this.menuService = menuService;

    }

    /* 전처리 메소드 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /* 메소드 실행 시작 시간을 request에 attribute로 저장한다. */
        System.out.println("preHandler 호출함...");
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        /* true를 반환하면 Controller의 Handler 메소드의 호출로 이어지고 false를 반환하면 호출하지 않는다. */
        return true;
    }

    /* 후처리 메소드 */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        //후처리 메소드이기 때문에 ModelAndView가 존재함
        /* 메소드 실행 종료 시간을 구해 시작 시간과 연산하여 응답에 포함시킨다. */
        System.out.println("postHandler 호출함...");
        long startTime = (Long) request.getAttribute("startTime");
        request.removeAttribute("startTime");
        long endTime = System.currentTimeMillis();
        modelAndView.addObject("interval", endTime - startTime);

    }

    /* 뷰가 렌더링된 이후 동작하는 메소드 */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("afterCompletion 호출함...");
        menuService.method();

    }

}