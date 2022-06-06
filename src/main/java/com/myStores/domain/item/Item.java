package com.myStores.domain.item;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @Column(name = "model_kind")
    private String modelKind;
    @Column(name = "model_number")
    private String modelNumber;
    @Column(name = "purchase_store")
    private String purchaseStore;

    @Column(name = "manufacturer_number")
    private String manufacturerNumber;

    @Column(name = "about_product")
    private String aboutProduct;

    @Column(name = "set_type")
    private String setType;

    @Column(name = "basic_mount")
    private double basicMount;
    @Column(name = "basic_color")
    private String basicColor;
    @Column(name = "margin")
    private int margin;
    @Column(name = "core_stone")
    private String coreStone;
    @Column(name = "stone_quantity")
    private String stoneQuantity;
    @Column(name = "discontinued")
    private String discontinued;

    @Column(columnDefinition = "TEXT")
    private String memo;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "item_wage_id")
    private ItemWage itemWage;


    //== 생성 메서드 ==//


    public Item(String modelKind, String modelNumber,
                String purchaseStore, String manufacturerNumber,
                String aboutProduct, String setType,
                double basicMount, String basicColor,
                String coreStone, String stoneQuantity,
                String discontinued, String memo,
                ItemWage itemWage, int margin) {
        this.modelKind = modelKind;
        this.modelNumber = modelNumber;
        this.purchaseStore = purchaseStore;
        this.manufacturerNumber = manufacturerNumber;
        this.aboutProduct = aboutProduct;
        this.setType = setType;
        this.basicMount = basicMount;
        this.margin = margin;
        this.basicColor = basicColor;
        this.coreStone = coreStone;
        this.stoneQuantity = stoneQuantity;
        this.discontinued = discontinued;
        this.memo = memo;
        this.itemWage = itemWage;
    }
}
