package com.myStores.domain.Good;

import com.myStores.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String model;

    @Column
    private String classification;

    @Column
    private Double mount;

    @Column(name = "inventory_status")
    private int inventoryStatus;

    @Column(name = "core_stone")
    private String coreStone;

    @Column(name = "purchase_store")
    private String purchaseStore;

    @Column(name = "cumulative_sale")
    private String cumulativeSale;

    @Column
    private int price;

    @Column(name = "stone_count")
    private String stoneCount;

    @Column(columnDefinition = "TEXT")
    private String memo;

    @Column
    private String ect;

    @Builder
    public Goods(String model, int price, String classification, Double mount, int inventoryStatus, String coreStone, String purchaseStore, String cumulativeSale, String stoneCount, String memo,String ect) {
        this.model = model;
        this.price = price;
        this.classification = classification;
        this.mount = mount;
        this.inventoryStatus = inventoryStatus;
        this.coreStone = coreStone;
        this.purchaseStore = purchaseStore;
        this.cumulativeSale = cumulativeSale;
        this.stoneCount = stoneCount;
        this.memo = memo;
        this.ect = ect;
    }
}
