package com.myStores.web.controller;

import com.myStores.domain.GoldPrice;
import com.myStores.domain.item.Item;
import com.myStores.domain.item.ItemWage;
import com.myStores.domain.item.WagePrice;
import com.myStores.repository.ItemSearch;
import com.myStores.service.GoldPriceService;
import com.myStores.service.ItemService;
import com.myStores.web.dto.CreateItemDto;
import com.myStores.web.dto.SearchItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
                             Model model, HttpServletRequest request) {
        List<SearchItemDto> findItems = itemService.findAllByModelNumber(itemSearch.getModelNumber());
        GoldPrice latestPrice = goldPriceService.getLatestPrice();

        if(latestPrice != null){
            model.addAttribute("goldPrice", latestPrice.getPrice());
        }
        model.addAttribute("items", findItems);

        return "form/item/itemSearch";
    }

    @GetMapping("/calculate")
    public String calculateForm() {
        return "form/item/itemCalculate";
    }

    @GetMapping("new")
    public String saveForm(Model model) {
        model.addAttribute("itemForm", new CreateItemDto());
        return "form/item/createItemForm";
    }

    @PostMapping("new")
    public String create(@Valid @ModelAttribute("itemForm") CreateItemDto form, BindingResult result) {

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
