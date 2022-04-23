package com.myStores.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/goods")
public class goodsController {

    @GetMapping("/search")
    public String searchForm(){
        return "form/goods/goodSearch";
    }

    @GetMapping("/calculate")
    public String calculateForm(){
        return "form/goods/goodCalculate";
    }
}
