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
        Item item = new Item("부엉이","목걸이",20.0,itemWage,1,
                "크리스탈","집",null,2,null,null);

        //when
        Long saveItemId = itemService.saveItem(item);

        //then
        assertEquals(item.getModel(),itemRepository.findOne(saveItemId).getModel());
    }
}