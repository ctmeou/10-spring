package com.ohgiraffers.comprehensive.board.dao;

import com.ohgiraffers.comprehensive.board.dto.BoardDTO;
import com.ohgiraffers.comprehensive.board.dto.ReplyDTO;
import com.ohgiraffers.comprehensive.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    int selectTotalCount(Map<String, String> searchMap);

    List<BoardDTO> selectBoardList(SelectCriteria selectCriteria);

    BoardDTO selectBoardDetail(Long no);

    void incrementBoardCount(Long no);

    void insertReply(ReplyDTO registReply);

    List<ReplyDTO> selectReplyList(ReplyDTO loadReply);
}
