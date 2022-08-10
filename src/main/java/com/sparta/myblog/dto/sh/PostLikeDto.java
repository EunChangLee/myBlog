package com.sparta.myblog.dto.sh;


import com.sparta.myblog.domain.ec.PostLike;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLikeDto {


    private boolean status;
    private Long postId; // 게시글 정보
    private String username; // 회원 정보

    public PostLikeDto(PostLike postLike) {
        this.status = true;
        this.postId = postLike.getPost().getPostId();
        this.username = postLike.getPostUser().getUsername();
    }
}
