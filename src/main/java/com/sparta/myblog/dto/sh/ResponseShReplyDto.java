package com.sparta.myblog.dto.sh;

import com.sparta.myblog.domain.ec.Comment;
import com.sparta.myblog.domain.ec.Reply;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseShReplyDto {
    private Long id;
    private String username;
    private String cotent;
    private int likeCount;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    public ResponseShReplyDto(Reply reply) {
        this.id = reply.getReplyId();
        this.username = reply.getPostUser().getUsername();
        this.cotent = reply.getContent();
        this.likeCount = reply.getLikeCount();
        this.createdAt = reply.getCreatedAt();
        this.modifiedAt = reply.getModifiedAt();

    }


}
