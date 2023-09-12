package com.example.lv5.entity;

import com.example.lv5.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    // 게시물(Post)의 id와 Comment의 postId 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id", updatable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "comment",orphanRemoval = true)
    private List<CommentLike> commentLikes = new ArrayList<>();



    public Comment(CommentRequestDto requestDto, Post post) {
        this.post = post;
        this.contents = requestDto.getContents();
        post.getComments().add(this);
    }

    public void update(CommentRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }

    public void setUser(User user) {
        this.user = user;
        user.getComments().add(this);
    }

}
