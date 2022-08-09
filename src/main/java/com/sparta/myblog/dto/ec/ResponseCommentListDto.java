package com.sparta.myblog.dto.ec;

import com.sparta.myblog.domain.ec.Reply;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseCommentListDto {
    private Long id;
    private String content;
    private int likeCount;
    private List<Reply> replyList = new ArrayList<>();
}
