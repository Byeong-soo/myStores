package com.myStores.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemWage {

    @Id @GeneratedValue
    @Column(name = "item_wage_id")
    private Long id;

    @OneToOne(mappedBy = "itemWage",fetch = FetchType.LAZY)
    private Item item;

    @Embedded
    private WagePrice wagePrice;

    public ItemWage(WagePrice wagePrice) {
        this.wagePrice = wagePrice;
    }
}
