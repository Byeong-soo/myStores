package com.myStores.service;

import com.myStores.domain.GoldPrice;
import com.myStores.repository.GoldPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.registry.LocateRegistry;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GoldPriceService {


    private final GoldPriceRepository goldPriceRepository;

    @Transactional
    public void savePrice(int price){
        GoldPrice goldPrice = GoldPrice.createGoldPrice(price);
        goldPriceRepository.save(goldPrice);
    }

    public GoldPrice getLatestPrice(){
        return goldPriceRepository.latestPrice();
    }
}
