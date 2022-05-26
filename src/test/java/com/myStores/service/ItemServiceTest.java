package com.myStores.service;

import com.myStores.domain.item.Item;
import com.myStores.domain.item.ItemWage;
import com.myStores.domain.item.WagePrice;
import com.myStores.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ItemServiceTest {

    @Autowired ItemService itemService;
    @Autowired ItemRepository itemRepository;

    @Test
    public void 제품_저장() throws Exception {
        //given
        WagePrice wagePrice = new WagePrice(0,1000,1000);
        ItemWage itemWage = new ItemWage(wagePrice);
        Item item = new Item("목걸이","부엉이0","부산공방","123",1.0,
                "초록","에메랄드",1,"N",null,itemWage,null);

        //when
        Long saveItemId = itemService.saveItem(item);

        //then
        assertEquals(item.getModelNumber(),itemRepository.findOne(saveItemId).getModelNumber());
    }

    @Test
    public void 제품명으로_검색() throws Exception {
        //given
        WagePrice wagePrice = new WagePrice(0,1000,1000);
        ItemWage itemWage = new ItemWage(wagePrice);
        Item item1 = new Item("목걸이","부엉이_1","부산공방","123",1.0,
                "초록","에메랄드",1,"N",null,itemWage,null);
        Long saveItemId1 = itemService.saveItem(item1);

        WagePrice wagePrice2 = new WagePrice(0,1000,1000);
        ItemWage itemWage2 = new ItemWage(wagePrice2);
        Item item2 = new Item("목걸이","부엉이_2","부산공방","123",1.0,
                "초록","에메랄드",1,"N",null,itemWage2,null);
        Long saveItemId2 = itemService.saveItem(item2);

        WagePrice wagePrice3 = new WagePrice(0,1000,1000);
        ItemWage itemWage3 = new ItemWage(wagePrice3);
        Item item3 = new Item("목걸이","거북이_2","부산공방","123",1.0,
                "초록","에메랄드",1,"N",null,itemWage3,null);
        Long saveItemId3 = itemService.saveItem(item3);

        //when
        List<Item> result1 = itemService.findAllByModelNumber("부엉이");
        List<Item> result2 = itemService.findAllByModelNumber("");

        //then
        assertEquals(result1.size(),2);
        assertEquals(result2.size(),3);
    }
}