package com.ehl3288.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository <Posts, Long> {

    List<Posts> findAllById(Long id);
}
