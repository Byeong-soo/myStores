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

    public GoldPrice latestPrice(){
        List<GoldPrice> resultList = em.createQuery("select g from GoldPrice g order by g.recordTime desc", GoldPrice.class)
                .setMaxResults(1)
                .getResultList();
        if(resultList.size()>0){
            return resultList.get(0);
        }
       return null;
    }


}
