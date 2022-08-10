package com.sparta.myblog.dto.sh;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.myblog.domain.ec.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseShCommentListDto {
    private Long id;
    private String content;
    private int likeCount;
    private List<ResponseShReplyDto> replyList = new ArrayList<>();

    @JsonIgnore
    private Comment comment;

    public ResponseShCommentListDto() {
    }

    public ResponseShCommentListDto(Comment comment, List<ResponseShReplyDto> replyList) {
        this.id = comment.getCommentId();
        this.content = comment.getContent();
        this.likeCount = comment.getLikeCount();
        this.replyList = replyList;
    }
    public ResponseShCommentListDto(Comment comment) {
        this.id = comment.getCommentId();
        this.content = comment.getContent();
        this.likeCount = comment.getLikeCount();
        this.comment = comment;
    }
}
