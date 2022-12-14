package com.sparta.myblog.dto;


import com.sparta.myblog.domain.PostUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostUserDto {
    private Long id;
    private String password;
    private String nickName;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static PostUserDto from(PostUser postUser) {
        if(postUser == null) return null;

        return PostUserDto.builder()
                .userName(postUser.getUsername())
                .nickName(postUser.getNickname())
                .build();
    }
}
