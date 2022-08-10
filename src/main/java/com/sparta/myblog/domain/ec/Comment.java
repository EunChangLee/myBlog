package com.sparta.myblog.domain.ec;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.myblog.domain.Timestamped;
import com.sparta.myblog.dto.ec.CommentDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "Comment")
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @Column
    private int likeCount;

    @JsonIgnore
    @OneToMany(mappedBy = "comment", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Reply> replies = new ArrayList<>();

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private PostUser postUser; // 회원 정보

    @JsonIgnore
    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Comment(Post post, CommentDto dto, PostUser postUser){
        this.content = dto.getContent();
        this.likeCount = 0;
        this.post = post;
        this.postUser = postUser;
    }
    public Comment(CommentDto dto){
        this.content = dto.getContent();
        //this.likeCount = 0;
    }

    public void update(CommentDto dto){
        this.content = dto.getContent();
    }
}
