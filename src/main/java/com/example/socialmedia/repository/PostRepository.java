package com.example.socialmedia.repository;

import com.example.socialmedia.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
