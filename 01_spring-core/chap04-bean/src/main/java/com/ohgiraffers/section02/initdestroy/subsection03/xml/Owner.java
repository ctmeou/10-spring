package com.ohgiraffers.section02.initdestroy.subsection03.xml;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Owner {

    public void openShop() {
        System.out.println("사장님이 가게 문을 열었습니다. 이제 쇼핑하실 수 있습니다.");
    }

    public void closeShop() {
        System.out.println("사장님이 가게 문을 닫았습니다. 이제 쇼핑하실 수 없습니다.");
    }

}