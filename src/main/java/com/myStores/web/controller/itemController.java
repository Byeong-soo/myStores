package com.myStores.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/item")
public class itemController {

    @GetMapping("/search")
    public String searchForm(){
        return "form/item/itemSearch";
    }

    @GetMapping("/calculate")
    public String calculateForm(){
        return "form/item/itemCalculate";
    }

    @GetMapping("save")
    public String saveForm(){
        return "form/item/itemSave";
    }
}
