package com.myStores.service;

import com.myStores.domain.item.Item;
import com.myStores.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public List<Item> findAllByModelNumber(String modelNumber){
        return itemRepository.findAllByModelNumber(modelNumber);
    }
}
