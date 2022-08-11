package com.sparta.myblog.dto;


import com.sparta.myblog.domain.CommentLike;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentLikeDto {


    private boolean status;
    private Long commentId; // 댓글 아이디
    private String username; // 댓글 쓴사람

    public CommentLikeDto(CommentLike commentLike) {
        this.status = true;
        this.commentId = commentLike.getComment().getCommentId();
        this.username = commentLike.getPostUser().getUsername();
    }
}
