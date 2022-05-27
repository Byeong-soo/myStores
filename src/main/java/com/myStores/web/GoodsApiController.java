package com.myStores.web;

import com.myStores.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class GoodsApiController {

    private final ItemService itemService;

//    @GetMapping("/api/test/goods")
//    public String countAll(){
//        return String.valueOf(goodsService.countAll());
//    }



}
