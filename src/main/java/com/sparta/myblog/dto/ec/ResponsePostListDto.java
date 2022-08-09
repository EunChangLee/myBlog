package com.sparta.myblog.dto.ec;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponsePostListDto {
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
