package com.ohgiraffers.sessionlogin.member.dao;

import com.ohgiraffers.sessionlogin.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper//Mybatis라는 어노테이션
public interface MemberMapper {

    MemberDTO findMemberById(String memberId);
    //memberId로 DB 조회
    //전달한 id를 기반으로 회원인지 일치 여부 확인
    void registMember(MemberDTO member);

    void addMemberAuthority();
}
