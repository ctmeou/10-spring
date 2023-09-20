package com.ohgiraffers.comprehensive.member.service;

import com.ohgiraffers.comprehensive.common.exception.member.MemberRegistException;
import com.ohgiraffers.comprehensive.member.dao.MemberMapper;
import com.ohgiraffers.comprehensive.member.dto.MemberDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public boolean selectMemberById(String memberId) {

        String result = memberMapper.selectMemberById(memberId);

        return result != null;
        //!=null인 경우 true

    }

    //두 개의 테이블에 insert되도록할 거고 transactional되어야 함
    @Transactional
    public void registMemebr(MemberDTO member) throws MemberRegistException {

        int result1 = memberMapper.insertMember(member);
        int result2 = memberMapper.insertMemberRole();

        if (!(result1 > 0 && result2 > 0)) throw new MemberRegistException("회원 가입에 실패하였습니다.");

    }
}
