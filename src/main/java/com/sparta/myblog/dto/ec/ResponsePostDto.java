package com.sparta.myblog.dto.ec;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponsePostDto {
    private Long postId; // 게시물 ID
    private String title; // 게시물 제목
    private String postContent; // 게시물 내용
    private int likeCount; // 좋아요 수
    private String image;
    public List<Co> commentList = new ArrayList<>();

//    private List<Comment> comments = new ArrayList<>();
//    private List<Reply> replys = new ArrayList<>();

    @Getter
    @Setter
    public static class Co {
        int likeCount;
        String content;
        LocalDateTime createdAt;
        LocalDateTime modifiedAt;
        public List<Re> reList = new ArrayList<>();
    }
    @Getter
    @Setter
    public static class Re {
        int likeCount;
        String content;
        LocalDateTime createdAt;
        LocalDateTime modifiedAt;
    }
}


