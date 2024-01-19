package com.lucy.springboot.service;

import com.lucy.springboot.domain.posts.Posts;
import com.lucy.springboot.domain.posts.PostsRepository;
import com.lucy.springboot.web.dto.PostsResponseDto;
import com.lucy.springboot.web.dto.PostsSaveRequestDto;
import com.lucy.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
  private final PostsRepository postsRepository;

  @Transactional
  public Long save(PostsSaveRequestDto requestDto) {
    return postsRepository.save(requestDto.toEntity()).getId();
  }

  @Transactional
  public Long update(Long id, PostsUpdateRequestDto requestDto) {
    Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다, id="+id));
    // update 시 쿼리를 날리는 부분이 없다.가능한 이유는 JPA의 영속성 컨텍스트 떄문이다.
    // 즉, 엔티티를 영구 저장하는 환경이기 때문
    posts.update(requestDto.getTitle(), requestDto.getContent());

    return id;
  }

  public PostsResponseDto findById (Long id) {
    Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다, id =" + id));

    return new PostsResponseDto(entity);
  }

}