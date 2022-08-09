package com.sparta.myblog.domain.ec;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.myblog.domain.Timestamped;
import com.sparta.myblog.domain.ec.Comment;
import com.sparta.myblog.dto.ec.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Posts")
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId; // 게시물 ID

    @Column(nullable = false)
    private String title; // 게시물 제목

    @Column(nullable = false)
    private String content; // 게시물 내용

    @Column
    private int likeCount; // 좋아요 수

    @Column
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>(); // 게시물 댓글 목록

//    @JoinColumn(name = "user_id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private PostUser postUser; // 회원 정보

    public Post(PostDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.likeCount = 0;
        //this.postUser = user;
    }

    public void update(PostDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.likeCount = 0;
    }
}
