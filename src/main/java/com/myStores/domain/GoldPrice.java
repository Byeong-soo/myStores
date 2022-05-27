package com.myStores.domain;

import com.myStores.repository.GoldPriceRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoldPrice {

    @Id @GeneratedValue
    private Long id;

    private LocalDateTime recordTime;
    private int price;

    public GoldPrice(LocalDateTime recordTime, int price) {
        this.recordTime = recordTime;
        this.price = price;
    }

    //== 생성자 메소드 ==//
    public static GoldPrice createGoldPrice(int price) {
        LocalDateTime now = LocalDateTime.now();
        GoldPrice goldPrice = new GoldPrice(now,price);
        return goldPrice;
    }
}
