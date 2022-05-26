package com.myStores.web.dto;

import com.myStores.domain.item.Item;
import com.myStores.domain.item.ItemWage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class CreateItemDto {

    private String modelNumber;
    private String modelKind;
    private String purchaseStore;
    private String purchaseStoreNumber;

    private Double basicMount;
    private String basicColor;

    private String coreStone;
    private int stoneQuantity;

    private String discontinued;

    private String memo;

    private int basicWage;
    private int mainWage;
    private int supportWage;

}
