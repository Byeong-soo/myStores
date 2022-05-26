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
    @Column(name = "purchase_store_number")
    private String purchaseStoreNumber;

    @Column(name = "basic_mount")
    private Double basicMount;
    @Column(name = "basic_color")
    private String basicColor;
    @Column(name = "core_stone")
    private String coreStone;
    @Column(name = "stone_quantity")
    private int stoneQuantity;
    @Column(name = "discontinued")
    private String discontinued;

    @Column(columnDefinition = "TEXT")
    private String memo;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "item_wage_id")
    private ItemWage itemWage;

    private String ect;

    //== 생성 메서드 ==//


    public Item(String modelKind, String modelNumber, String purchaseStore,
                String purchaseStoreNumber, Double basicMount,
                String basicColor, String coreStone,
                int stoneQuantity, String discontinued,
                String memo, ItemWage itemWage, String ect) {
        this.modelKind = modelKind;
        this.modelNumber = modelNumber;
        this.purchaseStore = purchaseStore;
        this.purchaseStoreNumber = purchaseStoreNumber;
        this.basicMount = basicMount;
        this.basicColor = basicColor;
        this.coreStone = coreStone;
        this.stoneQuantity = stoneQuantity;
        this.discontinued = discontinued;
        this.memo = memo;
        this.itemWage = itemWage;
        this.ect = ect;
    }
}
