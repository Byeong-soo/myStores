package com.myStores.web;

import com.myStores.service.ItemService;
import com.myStores.web.dto.SearchItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
public class itemApiController {

    private final ItemService itemService;

    @GetMapping("/api/item/{findById}")
    public SearchItemDto findByItemId(@PathVariable("findById")Long findItemId ){
        return itemService.findPriceById(findItemId);
    }



}
