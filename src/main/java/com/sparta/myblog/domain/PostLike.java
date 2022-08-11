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
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean status;

    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post; // 게시글 정보

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PostUser postUser; // 회원 정보

    public PostLike(Post post, PostUser postUser) {
        this.status = true;
        this.post = post;
        this.postUser = postUser;
    }
}
