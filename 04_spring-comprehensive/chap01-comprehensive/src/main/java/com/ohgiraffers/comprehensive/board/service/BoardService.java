package com.ohgiraffers.comprehensive.board.service;

import com.ohgiraffers.comprehensive.board.dao.BoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class BoardService {

    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }
    public Map<String, Object> selectBoardList(Map<String, String> searchMap, int page) {

        /* 1. 전체 게시글 수 확인(검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = boardMapper.selectTotalCount(searchMap); //조회요청(전체 게시글 중에 게시글이 몇 개인지)
        log.info("boarList totalCount : {}" + totalCount);
        return null;

    }
}
