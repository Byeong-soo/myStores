package com.myStores.repository;

import com.myStores.domain.item.Item;
import com.mysql.cj.log.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
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

    public List<Item> findAllBySearchWord(String searchWord) {

        String jpql = " select * From item where model_number like CONCAT('%',:searchWord,'%')" +
                " UNION ALL" +
                " select * From item where memo like CONCAT('%',:searchWord,'%')";

        try {
            double v = Double.parseDouble(searchWord);
            jpql += "UNION ALL " +
                    "select * FROM item where basic_mount = "+v;

        } catch (Exception e) {
            log.warn("숫자형이 아님");
        }


        List searchResult = em.createNativeQuery(jpql, Item.class).setParameter("searchWord", searchWord)
                .getResultList();

        return searchResult;

    }

    public List<Item> findAllByModelNumberPaging(String searchWord,int offset,int limit) {

        String jpql = " select * From item where model_number like CONCAT('%',:searchWord,'%')" +
                      " UNION ALL" +
                      " select * From item where memo like CONCAT('%',:searchWord,'%')";

        try {
            double v = Double.parseDouble(searchWord);
            jpql += "UNION ALL " +
                    "select * FROM item where basic_mount = "+v;

        } catch (Exception e) {
            log.warn("숫자형이 아님");
        }

//        String jpql2 = "select i From Item i where i.modelNumber like CONCAT('%',:searchWord,'%') ";
//        return em.createQuery(jpql,Item.class).setParameter("searchWord",searchWord)
//                .setFirstResult(offset)
//                .setMaxResults(limit)
//                .getResultList();

        List searchResult = em.createNativeQuery(jpql, Item.class).setParameter("searchWord", searchWord)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

        return searchResult;
    }

}
