package com.sparta.myblog.dto;

import com.sparta.myblog.domain.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseCommentDto {
    private Long id;
    private String content;
    private String postUser;
    private int commentLike;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;



    public ResponseCommentDto(Comment comment) {
        this.id = comment.getCommentId();
        this.content = comment.getContent();
        this.postUser = comment.getPostUser().getUsername();
        this.commentLike = comment.getLikeCount();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
