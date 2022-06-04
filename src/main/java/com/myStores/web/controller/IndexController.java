package com.myStores.web.controller;

import com.myStores.service.GoldPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {


    private final GoldPriceService goldPriceService;

    @GetMapping("/")
    public String index(Model model){//@LoginUser SessionUser user
//        model.addAttribute("posts",postsService.findAllDesc());
//        if(user != null){
//            model.addAttribute("loginName",user.getName());
//        }

        return "form/index";
    }

}
