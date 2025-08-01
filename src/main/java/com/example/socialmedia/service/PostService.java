package com.example.socialmedia.service;

import com.example.socialmedia.dto.PostCreateDto;
import com.example.socialmedia.dto.PostDto;
import com.example.socialmedia.exception.ResourceNotFoundException;
import com.example.socialmedia.model.Post;
import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.PostRepository;
import com.example.socialmedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public PostDto createPost(String username, PostCreateDto postCreateDto) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Post post = new Post();
        post.setContent(postCreateDto.getContent());
        post.setAuthor(author);
        post.setCreatedAt(java.time.LocalDateTime.now());
        post.setUpdatedAt(java.time.LocalDateTime.now());
        Post saved = postRepository.save(post);
        return toDto(saved);
    }

    public Page<PostDto> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable).map(this::toDto);
    }

    public Page<PostDto> getPostsByUsername(String username, Pageable pageable) {
        return postRepository.findByAuthor_Username(username, pageable).map(this::toDto);
    }

    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        return toDto(post);
    }

    public PostDto updatePost(Long id, String username, PostCreateDto dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        if (!post.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("You can only update your own posts");
        }
        post.setContent(dto.getContent());
        Post saved = postRepository.save(post);
        return toDto(saved);
    }

    public void deletePost(Long id, String username) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        if (!post.getAuthor().getUsername().equals(username)) {
            throw new RuntimeException("You can only delete your own posts");
        }
        postRepository.delete(post);
    }

    private PostDto toDto(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        dto.setAuthorUsername(post.getAuthor().getUsername());
        return dto;
    }
}
