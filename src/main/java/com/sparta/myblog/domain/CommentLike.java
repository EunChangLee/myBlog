package com.sparta.myblog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean status;

    @JoinColumn(name = "comment_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment; // 게시글 정보

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PostUser postUser; // 회원 정보

    public CommentLike(Comment comment, PostUser postUser) {
        this.status = true;
        this.comment = comment;
        this.postUser = postUser;
    }
}
