package com.ohgiraffers.comprehensive.common.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@AllArgsConstructor //객체를 매개변수로 선언할 거라서 매개변수 생성자 선언
public class SelectCriteria {
    //조회조건

    private int page;
    private int totalCount;
    private int limit;
    private int buttonAmount;
    private int maxPage;
    private int startPage;
    private int endPage;
    private int startRow;
    private int endRow;
    private String searchCondition;
    private String searchValue;

}
