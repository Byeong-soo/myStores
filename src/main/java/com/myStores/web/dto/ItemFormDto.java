package com.myStores.web.dto;

import com.myStores.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemFormDto {

    private String modelNumber;
    private String modelKind;
    private String purchaseStore;
    private String manufacturerNumber;

    private String aboutProduct;
    private String setType;

    private Double basicMount;
    private String basicColor;

    private String coreStone;
    private String stoneQuantity;

    private String discontinued;

    private String memo;

    private int basicWage;
    private int mainWage;
    private int supportWage;

    private int margin;

    public ItemFormDto(){

    }


    public ItemFormDto(Item item) {
        this.modelNumber = item.getModelNumber();
        this.modelKind = item.getModelKind();
        this.purchaseStore = item.getPurchaseStore();
        this.manufacturerNumber = item.getManufacturerNumber();
        this.aboutProduct = item.getAboutProduct();
        this.setType = item.getSetType();
        this.basicMount = item.getBasicMount();
        this.basicColor = item.getBasicColor();
        this.coreStone = item.getCoreStone();
        this.stoneQuantity = item.getStoneQuantity();
        this.discontinued = item.getDiscontinued();
        this.memo = item.getMemo();
        this.basicWage = item.getItemWage().getWagePrice().getBasic();
        this.mainWage = item.getItemWage().getWagePrice().getMain();
        this.supportWage = item.getItemWage().getWagePrice().getSupport();
        this.margin = item.getMargin();
    }
}
