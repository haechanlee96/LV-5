package com.example.lv5.service;

import com.example.lv5.dto.StatusResponseDto;
import com.example.lv5.entity.*;
import com.example.lv5.repository.CommentLikeRepository;
import com.example.lv5.repository.CommentRepository;
import com.example.lv5.repository.PostLikeRepository;
import com.example.lv5.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public ResponseEntity<?> toggleLikePost(Long id, User user) {
        // 좋아요 조회
        // 있으면 삭제
        // 없으면 생성
        StatusResponseDto msg = postLikeRepository.findByPostIdAndUserId(id, user.getId())
                .map(postLike -> {
                    postLikeRepository.delete(postLike);
                    return new StatusResponseDto("좋아요 취소 성공", 200);
                })
                .orElseGet(() -> {
                    createPostLikes(id, user);
                    return new StatusResponseDto("좋아요 성공", 200);
                });

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    private void createPostLikes(Long id, User user) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
        postLikeRepository.save(new PostLike(post, user));
    }

    public Long getLikesOfPost(Long postId) {
        return postLikeRepository.countByPostId(postId);
    }


    public ResponseEntity<?> toggleLikeComment(Long id, User user) {
        // 좋아요 조회
        // 있으면 삭제
        // 없으면 생성
        StatusResponseDto msg = commentLikeRepository.findByCommentIdAndUserId(id, user.getId())
                .map(commentLike -> {
                    commentLikeRepository.delete(commentLike);
                    return new StatusResponseDto("댓글 좋아요 취소 성공", 200);
                })
                .orElseGet(() -> {
                    createCommentLikes(id, user);
                    return new StatusResponseDto("댓글 좋아요 성공", 200);
                });

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    private void createCommentLikes(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 댓글은 존재하지 않습니다.")
        );
        commentLikeRepository.save(new CommentLike(comment, user));
    }

    public Long getLikesOfComment(Long commentId) {
        return commentLikeRepository.countByCommentId(commentId);
    }
}
