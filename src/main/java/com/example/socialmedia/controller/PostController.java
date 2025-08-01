package com.example.socialmedia.controller;

import com.example.socialmedia.dto.ApiResponse;
import com.example.socialmedia.dto.PostCreateDto;
import com.example.socialmedia.dto.PostDto;
import com.example.socialmedia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<ApiResponse<PostDto>> createPost(Authentication authentication, @RequestBody PostCreateDto dto) {
        String username = authentication.getName();
        PostDto created = postService.createPost(username, dto);
        return ResponseEntity.ok(ApiResponse.success("Post created", created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostDto>>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(ApiResponse.success("All posts fetched", posts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> getPostById(@PathVariable Long id) {
        PostDto post = postService.getPostById(id);
        return ResponseEntity.ok(ApiResponse.success("Post fetched", post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> updatePost(@PathVariable Long id, Authentication authentication, @RequestBody PostCreateDto dto) {
        String username = authentication.getName();
        PostDto updated = postService.updatePost(id, username, dto);
        return ResponseEntity.ok(ApiResponse.success("Post updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        postService.deletePost(id, username);
        return ResponseEntity.ok(ApiResponse.success("Post deleted", null));
    }
}
