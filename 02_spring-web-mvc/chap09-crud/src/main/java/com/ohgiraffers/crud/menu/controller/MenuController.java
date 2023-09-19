package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/menu")
public class MenuController {

    /* Logger 객체 생성 */
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    private final MenuService menuService;
    private final MessageSource messageSource;

    public MenuController(MenuService menuService, MessageSource messageSource) {
        //생성자를 통한 의존성 주입
        this.menuService = menuService;
        this.messageSource = messageSource;
    } //이것을 통해 메시지를 꺼냄

    @GetMapping("/list")
    public String findMenuList(Model model) {
    //model 객체르 받아와서 menuList에 저장한 후에 ___작성

        List<MenuDTO> menuList = menuService.findAllMenu();

        model.addAttribute("menuList", menuList);

        return "menu/list";

    }

    @GetMapping("/regist")
    public void registPage() {}

    @GetMapping("/category")
    public @ResponseBody List<CategoryDTO> findCategoryList() {

        return menuService.findAllCategory();
        
    }

    @PostMapping("/regist")
    public String registMenu(@ModelAttribute MenuDTO newMenu, Locale locale, RedirectAttributes rttr) {

        //System.out.println(newMenu); //프로그램 배포 시 이렇게 출력하는 것은 지양
        logger.info("newMenu : {}", newMenu); //{} 전체적인 문장의 템플릿
        logger.trace("locale : {}", locale); //가장 낮은 레벨
        logger.debug("locale : {}", locale); //그 다음 낮은 레벨
        logger.info("locale : {}", locale);
        logger.warn("locale : {}", locale);
        logger.error("locale : {}", locale);

        menuService.registNewMenu(newMenu);

        rttr.addFlashAttribute("successMessage", messageSource.getMessage("registMenu", null, locale)); //전달된 두 번째 값이 없어서 null

        //insert됐을 경우 클라이언트는 forward, redirect 방식 중 redirect 방식으로 전달 받음
        //forword는 다시 insert해야 됨
        return "redirect:/menu/list";

    }

}
