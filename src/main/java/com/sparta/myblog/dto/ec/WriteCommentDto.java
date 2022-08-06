package com.sparta.myblog.dto.ec;

import lombok.Getter;

@Getter
public class WriteCommentDto {
    private String nickName;
    private String content;
    private String createdAt;
    private String modifiedAt;
}
