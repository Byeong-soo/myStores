package com.myStores.web.dto;

import com.myStores.domain.item.Item;
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

//    public SearchItemDto(Long id, String modelNumber, Double basicMount, int margin, String memo, int basicWage, int mainWage, int supportWage, int sum) {
//        this.id = id;
//        this.modelNumber = modelNumber;
//        this.basicMount = basicMount;
//        this.margin = margin;
//        this.memo = memo;
//        this.basicWage = basicWage;
//        this.mainWage = mainWage;
//        this.supportWage = supportWage;
//        this.sum = sum;
//    }

    public SearchItemDto(Item item){
        this.id = item.getId();
        this.modelNumber = item.getModelNumber();
        this.basicMount = item.getBasicMount();
        this.margin = item.getMargin();
        this.memo = item.getMemo();
        this.basicWage = item.getItemWage().getWagePrice().getBasic();
        this.mainWage = item.getItemWage().getWagePrice().getMain();
        this.supportWage = item.getItemWage().getWagePrice().getSupport();
        this.sum = item.getItemWage().getWagePrice().getSum();
    }

}
