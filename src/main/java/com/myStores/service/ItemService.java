package com.myStores.service;

import com.myStores.domain.item.Item;
import com.myStores.domain.item.ItemWage;
import com.myStores.domain.item.WagePrice;
import com.myStores.repository.ItemRepository;
import com.myStores.web.dto.PriceInfo;
import com.myStores.web.dto.SearchItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long saveItem(Item item){
        validateDuplicateModel(item);
        itemRepository.save(item);
        return item.getId();
    }

    private void validateDuplicateModel(Item item) {
        List<Item> findItems = itemRepository.findByModelNumber(item.getModelNumber());
        if(!findItems.isEmpty()){
            throw new IllegalStateException("이미 존재하는 모델입니다.");
        }
    }

    public Item findById(Long itemId){
        return itemRepository.findById(itemId);
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public List<SearchItemDto> findAllByModelNumber(String modelNumber){

        List<Item> allByModelNumber = itemRepository.findAllByModelNumber(modelNumber);
        List<SearchItemDto> findItemList = new ArrayList<>();

        for (Item item : allByModelNumber) {
            SearchItemDto searchItemDto = new SearchItemDto(item);
            findItemList.add(searchItemDto);
        }

        return findItemList;
    }

    public List<SearchItemDto> findAllByModelNumberPaging(String modelNumber,int offset,int limit){

        List<Item> allByModelNumber = itemRepository.findAllByModelNumberPaging(modelNumber,offset,limit);
        List<SearchItemDto> findItemList = new ArrayList<>();

        for (Item item : allByModelNumber) {
            SearchItemDto searchItemDto = new SearchItemDto(item);
            findItemList.add(searchItemDto);
        }

        return findItemList;
    }

    public SearchItemDto findPriceById(Long findItemId){
        Item findItem = itemRepository.findById(findItemId);
        return  new SearchItemDto(findItem);
    }
    @Transactional
    public SearchItemDto updatePrice(Long findItemId, PriceInfo priceInfo){
        Item findItem = itemRepository.findById(findItemId);

        WagePrice wagePrice = new WagePrice(priceInfo.getBasicWage(),priceInfo.getMainWage(),priceInfo.getSupportWage());
        ItemWage itemWage = new ItemWage(wagePrice);

        findItem.changePrice(itemWage,priceInfo.getMargin());

        return  new SearchItemDto(findItem);
    }
}
