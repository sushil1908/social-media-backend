package com.example.socialmedia.repository;

import com.example.socialmedia.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByAuthor_Username(String username, Pageable pageable);
}
