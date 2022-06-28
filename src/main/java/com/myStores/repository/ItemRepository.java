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

    public void delete(Item item){
        em.remove(item);
    }


    public Item findById(Long goodsId){
        return em.find(Item.class,goodsId);
    }

    public List<Item> findAll() {
        return em.createQuery("select g from Item g", Item.class)
                .getResultList();
    }

    public List<Item> findByModelNumber(String modelNumber){
        return em.createQuery("select i from Item i where i.modelNumber = :modelNumber",Item.class)
                .setParameter("modelNumber", modelNumber)
                .getResultList();
    }

    //모델 명으로 검색2(수정필요)
    public List<Item> findAllByModelNumber(String modelNumber) {

        String jpql = "select i From Item i where i.modelNumber like CONCAT('%',:modelNumber,'%') ";
        return em.createQuery(jpql,Item.class).setParameter("modelNumber",modelNumber)
                .getResultList();
    }


    public List<Item> findAllByModelNumberPaging(String modelNumber,int offset,int limit) {

        String jpql = "select i From Item i where i.modelNumber like CONCAT('%',:modelNumber,'%') ";
        return em.createQuery(jpql,Item.class).setParameter("modelNumber",modelNumber)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

}
