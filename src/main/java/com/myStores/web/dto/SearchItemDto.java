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
    private int price14k;
    private int price18k;

    private int price14kCard;
    private int price18kCard;


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

    public void saveTotalPrice(SearchItemDto searchItemDto,int marketPrice){
        searchItemDto.price14k =(int) Math.round((searchItemDto.basicMount * marketPrice / 3.75 * 0.6435 + searchItemDto.sum + searchItemDto.margin) / 1000) * 1000;
        searchItemDto.price18k =(int) Math.round((searchItemDto.basicMount * 1.13 * marketPrice / 3.75 * 0.825 + searchItemDto.sum + searchItemDto.margin) / 1000) * 1000;

        if(searchItemDto.price14k>=1000000){
            searchItemDto.price14kCard =(int) Math.round((searchItemDto.getPrice14k() * 1.15)/1000)*1000;
        } else{
            searchItemDto.price14kCard =(int) Math.round((searchItemDto.getPrice14k() * 1.14)/1000)*1000;
        }

        if(searchItemDto.price18k>=1000000){
            searchItemDto.price18kCard =(int) Math.round((searchItemDto.getPrice18k() * 1.15)/1000)*1000;
        } else{
            searchItemDto.price18kCard =(int) Math.round((searchItemDto.getPrice18k() * 1.14)/1000)*1000;
        }

    }

}
