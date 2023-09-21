package com.ohgiraffers.comprehensive.board.dto;

import com.ohgiraffers.comprehensive.member.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.Date;

@Getter @Setter
@ToString
public class BoardDTO {

    private Long no;                //고유값
    private Integer type;           //사진 게시판인지, 일반 게시판인지
    private Long categoryCode;      //정확한 카테고리인지 모르기 때문에 DTO 필드로 가져
    private CategoryDTO category;
    private String title;           //제목
    private String body;            //내용
    private Long writerMemberNo;    //작성자 이름을 알고 싶으면 MemberDTO로 정보를 담아옴
    private MemberDTO writer;       //작성자
    private int count;              //조회 수
    private Date createdDate;       //작성일
    private Date modifiedDate;      //수정일
    private String status;          //삭제여부

}
