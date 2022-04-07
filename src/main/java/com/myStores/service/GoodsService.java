package com.myStores.service;

import com.myStores.domain.Good.GoodsRepository;
import com.myStores.web.dto.PostsListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GoodsService {

    private final GoodsRepository goodsRepository;

    @Transactional(readOnly = true)
    public Long countAll() {
        return goodsRepository.countAll();
    }

}
