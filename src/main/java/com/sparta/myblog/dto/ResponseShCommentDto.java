package com.sparta.myblog.dto;

import com.sparta.myblog.domain.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseShCommentDto {
    private Long id;
    private String username;
    private String content;
    private int likeCount;
    private int replyCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public ResponseShCommentDto(Comment comment, int replyCount) {
        this.id = comment.getCommentId();
        this.username = comment.getPostUser().getUsername();
        this.content = comment.getContent();
        this.likeCount = comment.getLikeCount();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.replyCount = replyCount;
    }


}
