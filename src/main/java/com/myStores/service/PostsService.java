package com.myStores.service;

import com.myStores.domain.posts.PostsRepository;
import com.myStores.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestsDto){
        return postsRepository.save(requestsDto.toEntity()).getId();
    }
}
