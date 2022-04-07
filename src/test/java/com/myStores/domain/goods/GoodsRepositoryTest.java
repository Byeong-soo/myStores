package com.myStores.domain.goods;

import com.myStores.domain.Good.Goods;
import com.myStores.domain.Good.GoodsRepository;
import com.myStores.domain.posts.Posts;
import com.myStores.domain.posts.PostsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GoodsRepositoryTest {

    @Autowired
    GoodsRepository goodsRepository;

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "123부엉이";

        List<Goods> goodsList = goodsRepository.findAll();

        //then
        Goods goods = goodsList.get(0);
        System.out.println("goods = " + goods.getModel());
//        assertThat(goods.getModel()).isEqualTo(title);
    }

    @Test
    public void 총갯수_세기(){

        Long aLong = goodsRepository.countAll();
        assertThat(aLong).isEqualTo(5927);
    }
}
