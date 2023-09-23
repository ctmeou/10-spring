package com.ohgiraffers.comprehensive.board.controller;

import com.ohgiraffers.comprehensive.board.dto.BoardDTO;
import com.ohgiraffers.comprehensive.board.dto.ReplyDTO;
import com.ohgiraffers.comprehensive.board.service.BoardService;
import com.ohgiraffers.comprehensive.member.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j //log 찍을 수 있게 선언
@Controller
@RequestMapping("/board")
public class BoardController {

    //호출을 위한 의존성 주입
    private final BoardService boardService;
    //게시판 내 검색 기능 어디 주석이었는지 찾기
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String getBoardList(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(required = false) String searchCondition,
                               @RequestParam(required = false) String searchValue,
                               //view에 표현하기 위해 model에 담음
                               Model model) {

        log.info("boardList page : {}", page); //파라미터 값 출력
        log.info("boardList searchCondition : {}", searchCondition);
        log.info("boardList searchValue : {}", searchValue);

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        //호출 Map으로 불러오는 이유?
        Map<String, Object> boardListAndPaging = boardService.selectBoardList(searchMap, page);
        model.addAttribute("paging", boardListAndPaging.get("paging"));
        model.addAttribute("boardList", boardListAndPaging.get("boardList"));
        //저장할 때와 꺼낼 떄의 Key 값과 이름이 일치해야 한다.(불일치 시 error)

        return "board/boardList";

    }

    @GetMapping("/detail")
    public String getBoardDetail(@RequestParam Long no, Model model) {

        BoardDTO boardDetail = boardService.selectBoardDetail(no);
        log.info("boardDetail : {}", boardDetail);
        model.addAttribute("board", boardDetail);

        return "board/boardDetail";

    }

    @PostMapping("/registReply")
    public ResponseEntity<String> registReply(@RequestBody ReplyDTO registReply,
                                              @AuthenticationPrincipal MemberDTO member) {
                                                //댓글 작성자 = 로그인한 사람

        //작성자 설정
        registReply.setWriter(member);
        log.info("registReply : {}", registReply);

        //data로 insert
        boardService.registReply(registReply);

        return  ResponseEntity.ok("댓글 등록 완료!");

    }

    @GetMapping("/loadReply")
    public ResponseEntity<List<ReplyDTO>> loadReply(ReplyDTO loadReply) {

        log.info("loadReply refBoardNo : {}", loadReply.getRefBoardNo());

        List<ReplyDTO> replyList = boardService.loadReply(loadReply);

        log.info("loadReply replyList : {}", replyList);

        return ResponseEntity.ok(replyList);

    }

    @PostMapping("/removeReply")
    public ResponseEntity<String> removeReply(@RequestBody ReplyDTO removeReply) {

        log.info("removeReply no : {}",removeReply.getNo());

        boardService.removeReply(removeReply);

        return ResponseEntity.ok("댓글 삭제 완료!");

    }

    //작성하기 누르면 페이지 이동하게
    @GetMapping("/regist")
    public String getRegistPage() {

        return "board/boardRegist";

    }

    /* boardRegist.html과 BoardMapper.xml을 참조하여 게시글 삽입이 되도록 구현 */
    @PostMapping("/regist")
    public String registBoard(BoardDTO board, @AuthenticationPrincipal MemberDTO member) {

        board.setWriter(member);
        log.info("registBoard board : {}", board);

        boardService.registBoard(board);

        return "redirect:/board/list";

    }

}
