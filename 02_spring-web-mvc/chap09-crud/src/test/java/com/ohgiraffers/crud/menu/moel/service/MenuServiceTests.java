package com.ohgiraffers.crud.menu.moel.service;

import com.ohgiraffers.crud.configuration.Chap09CrudApplication;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//테스트를 위한 컨테이너 구동
@ContextConfiguration(classes = { Chap09CrudApplication.class })
//컨테이너 구동을 설정을 동일하게 함(그대로 반영)
public class MenuServiceTests {

    @Autowired
    private MenuService menuService;
    //테스트 하고 싶은 것이 메뉴 서비스라 의존성, 호출 테스트

    @Test
    @Disabled
    public void 전체_메뉴_조회용_서비스_메소드_테스트() {
        // given
        //파라미터로 주어진 것이 없어 따로 기재 안 함
        // when
        List<MenuDTO> menuList =menuService.findAllMenu();
        // then
        assertNotNull(menuList);
        System.out.println(menuList);
    }

    @Test
    public void 신규_메뉴_등록용_서비스_성공_테스트() {
        // given
        MenuDTO menu = new MenuDTO();
        menu.setName("성공테스트");
        menu.setPrice(30000);
        menu.setCategoryCode(4);
        menu.setOrderableStatus("Y");

        // when & then
        assertDoesNotThrow(() -> menuService.registNewMenu(menu));
    }

    @Test
    public void 신규_메뉴_등록용_서비스_실패_테스트() {
        // given
        MenuDTO menu = new MenuDTO();
        menu.setName("실패테스트");
        menu.setPrice(30000);
        menu.setCategoryCode(100);
        menu.setOrderableStatus("Y");

        // when & then
        assertThrows(Exception.class, () -> menuService.registNewMenu(menu));
    }


}
