package com.myStores.domain.Good;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods,Long> {

//    List<Goods> findByModelContaining (String model);

    @Query("SELECT COUNT(g) FROM goods g")
    Long countAll();
}
