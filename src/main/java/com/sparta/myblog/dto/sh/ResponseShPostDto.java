package com.sparta.myblog.dto.sh;

import com.sparta.myblog.domain.ec.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseShPostDto {
    private Long id;
    private String title;
    private String content;
    private String username;
    private int likeCount;
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public ResponseShPostDto(Post post, int commentCount) {
        this.id = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = post.getPostUser().getUsername();
        this.likeCount = post.getLikeCount();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentCount = commentCount;
    }


}
