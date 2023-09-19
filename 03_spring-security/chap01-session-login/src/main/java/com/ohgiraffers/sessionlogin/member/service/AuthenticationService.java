package com.ohgiraffers.sessionlogin.member.service;

import com.ohgiraffers.sessionlogin.member.dao.MemberMapper;
import com.ohgiraffers.sessionlogin.member.dto.CustomUser;
import com.ohgiraffers.sessionlogin.member.dto.MemberDTO;
import com.ohgiraffers.sessionlogin.member.dto.MemberRoleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AuthenticationService implements UserDetailsService {

    private final MemberMapper memberMapper;

    public AuthenticationService(MemberMapper memberMapper) { this.memberMapper = memberMapper; }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //loger 객체도 lombok이 자동 생성해줌
        //어노테이션 붙이면 log를 사용해 loger를 사용하게 됨
        /* 전달된 아이디 확인 */
        log.info("username : {}", username);

        /* 사용자 정의 타입으로 유저 조회 */
        //의존성 주입 후 MemberDTO 호출
        MemberDTO member = memberMapper.findMemberById(username);

        /* 조회된 유저 확인 */
        log.info("member : {}", member);

        /* 일치하는 아이디가 없어서 조회된 유저가 없을 경우 */
        if (member == null) throw new UsernameNotFoundException("username not found");

        /* 권한 리스트 만들기 */
        //정해진 작성법이 있어 권한을 형식에 맞춤
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (MemberRoleDTO role : member.getMemberRoleList()) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority().getName()));
        }

        /* UserDetails를 구현한 User 객체에 id, pwd, 권한을 전달해서 객체를 생성하고 반환한다. */
        // return new User(member.getId(), member.getPwd(), authorities);

        /* User 객체에는 담을 수 없는 추가 정보를 User를 상속한 CustomUser로 처리한다. */
        return new CustomUser(member,authorities);
        //멤버의 정보와 권한 처리를 함

    }
}
