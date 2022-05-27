package com.myStores.service;

import com.myStores.domain.GoldPrice;
import com.myStores.repository.GoldPriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GoldPriceServiceTest {

    @Autowired
    GoldPriceService goldPriceService;

    @Test
    public void 가격저장() throws Exception {

        //given
        goldPriceService.savePrice(20000);

        //when
        List<GoldPrice> latestPrice = goldPriceService.getLatestPrice();

        //then
        assertEquals(latestPrice.get(0).getPrice(),20000);
    }

    @Test
    public void 최신가격가져오기() throws Exception {

        //given
        goldPriceService.savePrice(20000);
        goldPriceService.savePrice(1220000);
        goldPriceService.savePrice(120000);

        //when
        List<GoldPrice> latestPrice = goldPriceService.getLatestPrice();

        //then
        assertEquals(latestPrice.get(0).getPrice(),120000);
    }

}