package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.configuration.Chap09CrudApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@ContextConfiguration(classes = { Chap09CrudApplication.class })
public class MenuControllerTests {

    @Autowired
    private MenuController menuController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(menuController).build();
        //실제로 WAS 서버를 구동해서 http 요청을 보내는 것은 아니지만 실제 수행하는 것처럼 처리할 수 있는 객체
    }

    @Test
    public void 전체_메뉴_조회용_컨트롤러_테스트() throws Exception {
        // given

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/menu/list"))
                //perform 수행하다  get 메소드를 통해
                .andExpect(MockMvcResultMatchers.status().isOk()) //수행하는 결과 예측 status가 Ok일 것
                .andExpect(MockMvcResultMatchers.forwardedUrl("menu/list"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void 전체_카테고리_조회용_컨트롤로_테스트() throws Exception{
        // given

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/menu/category"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void 신규_메뉴_등록용_컨트롤러_테스트() throws Exception {
        // given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "컨트롤러테스트");
        params.add("price", "20000");
        params.add("categoryCode", "5");
        params.add("orderableStatus", "Y");

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/menu/regist").params(params))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/menu/list"))
                .andDo(MockMvcResultHandlers.print());
    }

}
