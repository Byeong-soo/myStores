package com.myStores.web.controller.api;

import com.myStores.domain.GoldPrice;
import com.myStores.service.GoldPriceService;
import com.myStores.service.ItemService;
import com.myStores.web.dto.PriceInfo;
import com.myStores.web.dto.SearchItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
public class itemApiController {

    private final ItemService itemService;
    private final GoldPriceService goldPriceService;

    @GetMapping("/api/item/{findById}")
    public SearchItemDto findByItemId(@PathVariable("findById")Long findItemId ){
        return itemService.findPriceById(findItemId);
    }

    @GetMapping("/api/item/goldPrice")
    public int getGoldPrice(){
        GoldPrice latestPrice = goldPriceService.getLatestPrice();
        return latestPrice.getPrice();
    }

    @PutMapping("/api/item/{findById}")
    public SearchItemDto savePrice(@PathVariable("findById")Long findItemId,
                                   @RequestBody PriceInfo priceInfo){
        return itemService.updatePrice(findItemId,priceInfo);
    }


}
