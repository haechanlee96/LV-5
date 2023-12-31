package com.example.lv5.controller;

import com.example.lv5.dto.CommentRequestDto;
import com.example.lv5.dto.CommentResponseDto;
import com.example.lv5.dto.StatusResponseDto;
import com.example.lv5.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 1. 게시글 작성
    @PostMapping("/comment")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(requestDto);
    }

    // 4. 게시글 수정
    @PutMapping("/comment/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.updateComment(id, commentRequestDto);
    }

    // 5. 게시글 삭제
    @DeleteMapping("/comment/{id}")
    public StatusResponseDto deleteComment(@PathVariable Long id) {
        return commentService.deletePost(id);
    }


}
