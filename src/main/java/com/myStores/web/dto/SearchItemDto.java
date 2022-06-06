package com.myStores.web.dto;

import lombok.Getter;

@Getter
public class SearchItemDto {

    private Long id;

    private String modelNumber;
    private Double basicMount;
    private int margin;
    private String memo;

    private int basicWage;
    private int mainWage;
    private int supportWage;

    private int sum;

    public SearchItemDto(Long id, String modelNumber, Double basicMount, int margin, String memo, int basicWage, int mainWage, int supportWage, int sum) {
        this.id = id;
        this.modelNumber = modelNumber;
        this.basicMount = basicMount;
        this.margin = margin;
        this.memo = memo;
        this.basicWage = basicWage;
        this.mainWage = mainWage;
        this.supportWage = supportWage;
        this.sum = sum;
    }
}
