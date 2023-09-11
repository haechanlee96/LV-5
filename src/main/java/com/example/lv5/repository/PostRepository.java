package com.example.lv5.repository;

import com.example.lv5.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @EntityGraph(attributePaths = "comments")
    List<Post> findAllByOrderByModifiedAtDesc();

    Post findPostById(Long id);
}

