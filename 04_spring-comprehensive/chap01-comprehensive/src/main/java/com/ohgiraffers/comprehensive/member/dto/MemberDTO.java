package com.ohgiraffers.comprehensive.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter @Setter @ToString
public class MemberDTO implements UserDetails {

    private Long memberNo;
    private String memberId;
    private String memberPwd;
    private String nickname;
    private String phone;
    private String email;
    private String address;
    private Date enrollDate;
    private String memberStatus;
    private List<MemberRoleDTO> memberRoleList;

    //Authorities에 대해 정해진 형태로 반환
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for(MemberRoleDTO role : memberRoleList) {
            roles.add(new SimpleGrantedAuthority(role.getAuthority().getName()));
        }
        return roles;
    }

    //패스워드와 이름을 알맞게 반환
    @Override
    public String getPassword() {
        return memberPwd;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    //계정이 만료되었는지, 활성화되었는지
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
