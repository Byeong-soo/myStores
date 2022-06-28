package com.myStores.web.controller;

import com.myStores.domain.GoldPrice;
import com.myStores.domain.item.Item;
import com.myStores.domain.item.ItemWage;
import com.myStores.domain.item.WagePrice;
import com.myStores.repository.ItemSearch;
import com.myStores.service.GoldPriceService;
import com.myStores.service.ItemService;
import com.myStores.web.dto.ItemFormDto;
import com.myStores.web.dto.SearchItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class itemController {

    private final ItemService itemService;
    private final GoldPriceService goldPriceService;

    @GetMapping("/search")
    public String searchForm(@ModelAttribute("itemSearch") ItemSearch itemSearch,
                             @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                             Model model) {

        int searchCount = itemService.findAllByModelNumber(itemSearch.getModelNumber()).size();
        int totalPage = 0;
        if(searchCount>0){
            if(searchCount%10==0)totalPage = searchCount/10;
            if(searchCount%10!=0)totalPage = searchCount/10 + 1;
        }

        int offset = 0;
        if(pageNum <=1) offset = 0;
        if(pageNum >1) offset = (pageNum-1)*10;
        int limit = 10;

        List<SearchItemDto> findItems = itemService.findAllByModelNumberPaging(itemSearch.getModelNumber(),offset,limit);
        GoldPrice latestPrice = goldPriceService.getLatestPrice();

        if(latestPrice != null){
            model.addAttribute("searchCount",searchCount);
            model.addAttribute("goldPrice", latestPrice.getPrice());
        }
        model.addAttribute("keyWord",itemSearch.getModelNumber());
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("items", findItems);
        return "form/item/itemSearch";
    }

    @GetMapping("/calculate")
    public String calculateForm() {
        return "form/item/itemCalculate";
    }

    @GetMapping("new")
    public String saveForm(Model model) {
        model.addAttribute("itemForm", new ItemFormDto());
        return "form/item/createItemForm";
    }

    @PostMapping("new")
    public String create(@Valid @ModelAttribute("itemForm") ItemFormDto form, BindingResult result) {

        if (result.hasErrors()) {
            return "form/item/createItemForm";
        }

        WagePrice wagePrice = new WagePrice(form.getBasicWage(), form.getMainWage(), form.getSupportWage());
        ItemWage itemWage = new ItemWage(wagePrice);

        Item item = new Item(form.getModelKind(), form.getModelNumber(),
                form.getPurchaseStore(), form.getManufacturerNumber(),
                form.getAboutProduct(),form.getSetType(),
                form.getBasicMount(), form.getBasicColor(),
                form.getCoreStone(), form.getStoneQuantity(),
                form.getDiscontinued(), form.getMemo(),
                itemWage,form.getMargin());

        itemService.saveItem(item);

        return "redirect:/item/search";
    }

    @GetMapping("/goldPrice")
    public String goldMarketCondition(Model model) {

        GoldPrice latestPrice = goldPriceService.getLatestPrice();
        model.addAttribute("latestPrice", latestPrice);

        return "form/item/goldPrice";
    }

    @PostMapping("/saveGoldPrice")
    public String alterGoldPrice(@RequestParam int goldPrice, HttpServletResponse response) {
        goldPriceService.savePrice(goldPrice);
        return "redirect:/item/goldPrice";
    }

    @GetMapping("/edit/{id}")
    public String updateItemForm(@PathVariable("id")Long id,Model model){
        ItemFormDto updateFormDto = itemService.getItemForm(id);
        model.addAttribute("updateItemId",id);
        model.addAttribute("updateItemInfo",updateFormDto);
        return  "form/item/updateItemForm";
    }

    @PostMapping("/edit/{id}")
    public String updateItem(@PathVariable Long id,@ModelAttribute("updateItemInfo")ItemFormDto updateItemInfo,Model model){

        String modelNumber = itemService.updateItem(id, updateItemInfo);
        String encodeModelNumber = URLEncoder.encode(modelNumber, StandardCharsets.UTF_8);
        return "redirect:/item/search?modelNumber="+encodeModelNumber;
    }

    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id){

        itemService.deleteItem(id);

        return "redirect:/item/search";
    }

    private void addCookie(HttpServletResponse response, String cookieName) {
        // 쿠키 추가
        GoldPrice latestPrice = goldPriceService.getLatestPrice();
        int price = latestPrice.getPrice();
        Cookie goldPriceCookie = new Cookie(cookieName, String.valueOf(price));
        response.addCookie(goldPriceCookie);
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }


}
