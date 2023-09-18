package com.ohgiraffers.crud.menu.model.service;

import com.ohgiraffers.crud.menu.model.dao.MenuMapper;
import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    private final MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
        //생성자를 통한 주입
    }

    public List<MenuDTO> findAllMenu() {
        //스프링 환경이 아닌 곳에서는 sqlSession을 얻어와 처리하거나 mapper를 꺼내서 처리했으나 그 방식이 필요 없음
        //데이터베이스 연동정보 기입, 메퍼 배치(yml)을 하면 스프링 안에서 이루어짐
        //스프링 환경에서는 SqlSession을 만들고 꺼내 쓸 필요가 없음
        //Configuration을 할 필요가 없음 mapper에 있는 메소드 호출만 하면 됨
        //서비스에서 해줘야 할 건 순수한 비즈니스 로직만 하면 됨
        return menuMapper.findAllMenu(); //수행할 코드만 작성
    }

    public List<CategoryDTO> findAllCategory() { return menuMapper.findAllCategory(); }

    @Transactional //@Transactional : 아래 메소드 수행하는 동안 익셉션 발생하지 않았으면 수행(commit)되고 exception 발생하면 rollback이 알아서 됨
    public void registNewMenu(MenuDTO newMenu) {
        menuMapper.registMenu(newMenu);
    }
}
