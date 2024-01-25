package com.lucy.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// posts 클래스로 DB를 접근하게 해주는 JpaRespository, DB Layer 접근자
// JpaRepository<Posts, Long> 를 상속하는 경우 : 기본적인 CRUD 메소드가 자동으로 생성된다
// 주의할 점 : entiry 클래스와 함께 위치해야 한다. Entity 클래스는 기본 Repository 없이 제대로 역할을 할 수가 없다.
public interface PostsRepository extends JpaRepository<Posts, Long> {

  @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
  List<Posts> findAllDesc();


}