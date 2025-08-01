package com.example.socialmedia.controller;

import com.example.socialmedia.dto.ApiResponse;
import com.example.socialmedia.dto.CommentCreateDto;
import com.example.socialmedia.dto.CommentDto;
import com.example.socialmedia.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<CommentDto>> addComment(
            @PathVariable Long postId,
            Authentication authentication,
            @RequestBody CommentCreateDto dto
    ) {
        String username = authentication.getName();
        CommentDto created = commentService.addComment(postId, username, dto);
        return ResponseEntity.ok(ApiResponse.success("Comment added", created));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<Page<CommentDto>>> getCommentsForPost(
            @PathVariable Long postId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<CommentDto> comments = commentService.getCommentsForPost(postId, pageable);
        return ResponseEntity.ok(ApiResponse.success("Comments fetched", comments));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(
            @PathVariable Long commentId,
            Authentication authentication
    ) {
        String username = authentication.getName();
        commentService.deleteComment(commentId, username);
        return ResponseEntity.ok(ApiResponse.success("Comment deleted", null));
    }
}
