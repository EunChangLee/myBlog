package com.sparta.myblog.dto.ec;

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
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
