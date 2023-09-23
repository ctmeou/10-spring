package com.ohgiraffers.comprehensive.board.service;

import com.ohgiraffers.comprehensive.board.dao.BoardMapper;
import com.ohgiraffers.comprehensive.board.dto.AttachmentDTO;
import com.ohgiraffers.comprehensive.board.dto.BoardDTO;
import com.ohgiraffers.comprehensive.board.dto.ReplyDTO;
import com.ohgiraffers.comprehensive.common.paging.Pagenation;
import com.ohgiraffers.comprehensive.common.paging.SelectCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class BoardService {

    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public Map<String, Object> selectBoardList(Map<String, String> searchMap, int page) {

        /* 1. 전체 게시글 수 확인(검색어가 있는 경우 포함) => 페이징 처리를 위해 */
        int totalCount = boardMapper.selectTotalCount(searchMap); //조회요청(전체 게시글 중에 게시글이 몇 개인지)
        log.info("boarList totalCount : {}", totalCount);

        /* 2. 페이징 처리와 연관된 값을 계산하여 SelectCriteria 타입의 객체에 담는다. */
        int limit = 10;         // 한 페이지에 보여줄 게시물의 수
        int buttonAmount = 5;   // 한 번에 보여질 페이징 버튼의 수
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
        //SelectCriteria : 조회 조건                                //계산을 해야 볼 수 있음
        log.info("boardList selectCriteria : {}", selectCriteria);

        /* 3. 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        //실질적으로 검색하는 페이지
        List<BoardDTO> boardList = boardMapper.selectBoardList(selectCriteria);
        log.info("boardList : {}", boardList);

        Map<String, Object> boardListAndPaging = new HashMap<>();
        boardListAndPaging.put("paging", selectCriteria);
        boardListAndPaging.put("boardList", boardList);

        return boardListAndPaging;

    }

    public BoardDTO selectBoardDetail(Long no) {

        /* 조회수 증가 로직 호출 */
        boardMapper.incrementBoardCount(no);

        /* 게시글 상세 내용 조회 후 리턴 */
        return boardMapper.selectBoardDetail(no);

    }

    public void registReply(ReplyDTO registReply) {

        boardMapper.insertReply(registReply);

    }

    public List<ReplyDTO> loadReply(ReplyDTO loadReply) {

        return  boardMapper.selectReplyList(loadReply);

    }

    public void removeReply(ReplyDTO removeReply) {

        boardMapper.deleteReply(removeReply);

    }

    public void registBoard(BoardDTO board) {
                                    //1
        boardMapper.insertBoard(board);
                                //1을 insert

    }
    public void registThumbnail(BoardDTO board) {

        /* Board 테이블에 데이터 저장 */
        boardMapper.insertThumbnailContent(board);

        /* Attachment 테이블에 데이터 저장(첨부 파일 갯수 만큼) */
        for (AttachmentDTO attachment : board.getAttachmentList()) {
            boardMapper.insertAttachment(attachment);
        }

    }

    public Map<String, Object> selectThumbnailList(int page) {

        //페이징 처리하기 위해 전체 페이지가 몇 페이지인지 확인
        int totalCount = boardMapper.selectThumbnailTotalCount();
        log.info("thumbnail totalcount : {}", totalCount);

        //필요한 설정으로 selectCriteria 만듦
        int limit = 9; //9개씩 띄움
        int buttonAmount = 5; //버튼 아래 몇 개 뜨는지 설정
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount);
        //검색 키워드 없이 컨텐츠만
        log.info("thumbnail selectCriteria : {}", selectCriteria);

        //selectCriteria 조희
        List<BoardDTO> thumbnailList = boardMapper.selectThumbnailBoardList(selectCriteria);
        log.info("thumbnail thumbnailList : {}", thumbnailList);

        Map<String, Object> thumbnailListAndPaging = new HashMap<>();
        thumbnailListAndPaging.put("paging", selectCriteria);
        thumbnailListAndPaging.put("thumbnailList", thumbnailList);

        return thumbnailListAndPaging;

    }

    public BoardDTO selectThumbnailDetail(Long no) {

        /* 조회수 증가 로직 호출 */
        boardMapper.incrementBoardCount(no);

        /* 사진 게시글 조회 후 반환 */
        return boardMapper.selectThumbnailBoardDetail(no);

    }

}
