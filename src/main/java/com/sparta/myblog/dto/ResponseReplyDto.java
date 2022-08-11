package com.sparta.myblog.dto;

import com.sparta.myblog.domain.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResponseReplyDto {
    private Long id;
    private String content;
    private String username;
    private int replyLike;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ResponseReplyDto(Reply reply) {
        this.id = reply.getReplyId();
        this.content = reply.getContent();
        this.username = reply.getPostUser().getUsername();
        this.replyLike = reply.getLikeCount();
        this.createdAt = reply.getCreatedAt();
        this.modifiedAt = reply.getModifiedAt();
    }
}
