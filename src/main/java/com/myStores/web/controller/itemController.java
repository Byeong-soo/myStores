package com.myStores.web.controller;

import com.myStores.domain.item.Item;
import com.myStores.domain.item.ItemWage;
import com.myStores.domain.item.WagePrice;
import com.myStores.service.ItemService;
import com.myStores.web.dto.CreateItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
public class itemController {

    private final ItemService itemService;

    @GetMapping("/search")
    public String searchForm(){
        return "form/item/itemSearch";
    }

    @GetMapping("/calculate")
    public String calculateForm(){
        return "form/item/itemCalculate";
    }

    @GetMapping("new")
    public String saveForm(Model model){
        model.addAttribute("itemForm",new CreateItemDto());
        return "form/item/createItemForm";
    }

    @PostMapping("new")
    public String create(@Valid @ModelAttribute("itemForm") CreateItemDto form, BindingResult result){

        if(result.hasErrors()){
            return "form/item/createItemForm";
        }

        WagePrice wagePrice = new WagePrice(form.getBasicWage(), form.getMainWage(), form.getSupportWage());
        ItemWage itemWage = new ItemWage(wagePrice);

        Item item = new Item(form.getModelKind(),form.getModelNumber(),
                form.getPurchaseStore(),form.getPurchaseStoreNumber(),
                form.getBasicMount(),form.getBasicColor(),
                form.getCoreStone(),form.getStoneQuantity(),
                form.getDiscontinued(),form.getMemo(),
                itemWage,form.getMemo());

        itemService.saveItem(item);

        return "redirect:/item/search";
    }
}
