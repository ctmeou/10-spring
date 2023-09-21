package com.ohgiraffers.comprehensive.board.controller;

import com.ohgiraffers.comprehensive.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Slf4j //log 찍을 수 있게 선언
@Controller
@RequestMapping("/board")
public class BoardController {

    //호출을 위한 의존성 주입
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String getBoardList(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(required = false) String searchCondition,
                               @RequestParam(required = false) String searchValue) {

        log.info("boardList page : {}", page); //파라미터 값 출력
        log.info("boardList searchCOndtion : {}", searchCondition);
        log.info("boardList searchValue : {}", searchValue);

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        //호출 Map으로 불러오는 이유?
        Map<String, Object> boardListAndPaging = boardService.selectBoardList(searchMap, page);

        //게시판 내 검색 기능

        return "board/boardList";

    }
}
