package com.ohgiraffers.sessionlogin.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class MemberDTO {

    private int no;
    private String id;
    private String pwd;
    private String tempPwdYn;
    private Date pwdChangedDatetime;
    private String pwdExpDate;
    private String name;
    private Date registDatetime;

    //member쪽에서 memberRole를 참조 한 명의 멤버가 여러 개의 memberRole을 가질 수 있음
    /* 한 멤버는 여러 권한을 가질 수 있다. */
    private List<MemberRoleDTO> memberRoleList;

}
