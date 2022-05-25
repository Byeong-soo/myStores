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

    private String model;

    @Column
    private String classification;

    @Column
    private Double mount;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "item_wage_id")
    private ItemWage itemWage;

    @Column(name = "inventory_status")
    private int inventoryStatus;

    @Column(name = "core_stone")
    private String coreStone;

    @Column(name = "purchase_store")
    private String purchaseStore;

    @Column(name = "cumulative_sale")
    private String cumulativeSale;

    @Column(name = "stone_count")
    private int stoneCount;

    @Column(columnDefinition = "TEXT")
    private String memo;

    private String ect;

    //== 생성 메서드 ==//

    public Item(String model, String classification, Double mount, ItemWage itemWage,
                int inventoryStatus, String coreStone, String purchaseStore,
                String cumulativeSale, int stoneCount, String memo, String ect) {
        this.model = model;
        this.classification = classification;
        this.mount = mount;
        this.itemWage = itemWage;
        this.inventoryStatus = inventoryStatus;
        this.coreStone = coreStone;
        this.purchaseStore = purchaseStore;
        this.cumulativeSale = cumulativeSale;
        this.stoneCount = stoneCount;
        this.memo = memo;
        this.ect = ect;
    }
}
