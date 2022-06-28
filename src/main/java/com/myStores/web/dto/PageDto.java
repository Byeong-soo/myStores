package com.myStores.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PageDto {

    int totalPage;
    int presentPage;
    int keyWord;

    int offset;
    int limit;

}
