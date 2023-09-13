package com.ohgiraffers.section02.initdestroy.subsection03.xml;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import com.ohgiraffers.section02.initdestroy.subsection02.annotation.ContextConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context
                = new GenericXmlApplicationContext("section02/initdestroy/subsection03/xml/spring-context.xml");

        /* 슈퍼에 상품이 진열되어 있다. */
        Product carpBread = context.getBean("carpBread", Bread.class);
        Product milk = context.getBean("milk", Beverage.class);
        Product water = context.getBean("water", Beverage.class);

        /* 첫 번째 손님이 쇼핑 카트를 꺼내 상품을 쇼핑 카트에 담는다. */
        ShoppingCart cart1 = context.getBean("cart", ShoppingCart.class);
        cart1.addItem(carpBread);
        cart1.addItem(milk);

        /* 두 번째 손님이 쇼핑 카트를 꺼내 상품을 쇼핑 카트에 담는다. */
        ShoppingCart cart2 = context.getBean("cart", ShoppingCart.class);
        cart2.addItem(water);

        System.out.println("cart1에 담긴 상품 : " + cart1.getItem());
        System.out.println("cart2에 담긴 상품 : " + cart2.getItem());

        System.out.println("cart1의 hashcode : " + cart1.hashCode());
        System.out.println("cart2의 hashcode : " + cart2.hashCode());

        /* init method는 빈 객체 생성 시점에 동작하므로 바로 확인할 수 있지만,
        * destroy method는 빈 객체 소멸 시점에 동작하므로 컨테이너가 종료되지 않을 경우 확인할 수 없다.
        * 아래와 같이 강제로 컨테이너를 종료시키면 destroy method의 동작을 확인할 수 있다. */
        ((GenericXmlApplicationContext)context).close();

    }

}