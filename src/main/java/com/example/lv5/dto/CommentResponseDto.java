package com.example.lv5.dto;

import com.example.lv5.entity.Comment;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long postId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String contents;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String username;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime modifiedAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer statusCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long commentLike;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.postId = comment.getPost().getId();
        this.contents = comment.getContents();
        this.username = comment.getUser().getUsername();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.commentLike = (long) comment.getCommentLikes().size();
    }

    public CommentResponseDto(String msg, Integer statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }

    public CommentResponseDto(String msg, Integer statusCode, Long commentLike) {
        this.msg = msg;
        this.statusCode = statusCode;
        this.commentLike = commentLike;
    }
}
