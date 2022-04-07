package com.myStores.web;

import com.myStores.service.GoodsService;
import com.myStores.service.PostsService;
import com.myStores.web.dto.PostsResponseDto;
import com.myStores.web.dto.PostsSaveRequestDto;
import com.myStores.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class GoodsApiController {

    private final GoodsService goodsService;

    @PostMapping("/api/v1/goods")
    public String save(){
        return String.valueOf(goodsService.countAll());
    }



}
