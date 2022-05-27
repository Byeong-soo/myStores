package com.myStores.repository;

import com.myStores.domain.GoldPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GoldPriceRepository {

    private final EntityManager em;

    public void save(GoldPrice goldPrice){
        em.persist(goldPrice);
    }

    public List<GoldPrice> latestPrice(){
        return em.createQuery("select g from GoldPrice g order by g.recordTime desc", GoldPrice.class)
                .setMaxResults(1)
                .getResultList();
    }


}
