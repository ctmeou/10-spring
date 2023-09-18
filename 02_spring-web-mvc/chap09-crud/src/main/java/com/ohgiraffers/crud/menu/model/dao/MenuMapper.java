package com.ohgiraffers.crud.menu.model.dao;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//스프링 환경에서 마이바티스 만든다면 @Mapper 추가
@Mapper
public interface MenuMapper {

    List<MenuDTO> findAllMenu(); //list형태로 반환
    
    List<CategoryDTO> findAllCategory();

    void registMenu(MenuDTO newMenu);

}
