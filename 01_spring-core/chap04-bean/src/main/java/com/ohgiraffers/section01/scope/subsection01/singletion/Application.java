package com.ohgiraffers.section01.scope.subsection01.singletion;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import com.ohgiraffers.section01.scope.subsection02.prototype.ContextConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

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

        /* SpringFramework에서 Bean의 기본 스코프는 singleton이다.
         * singleton 스코프를 갖는 bean은 어플리케이션 내에서 유일한 인스턴스를 갖는다.
         * 예제에서 손님 두 명이 각각 쇼핑 카트를 이용해 상품을 담으려고 했지만 cart도 singleton으로 관리되기 때문에
         * 두 손님이 동일한 카트에 물건을 담은 것이 된다. */
    }

}