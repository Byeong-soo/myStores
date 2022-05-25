package com.myStores.repository;

import com.myStores.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        em.persist(item);
    }

    public Item findOne(Long goodsId){
        return em.find(Item.class,goodsId);
    }

    public List<Item> findAll() {
        return em.createQuery("select g from Item g", Item.class)
                .getResultList();
    }



}
