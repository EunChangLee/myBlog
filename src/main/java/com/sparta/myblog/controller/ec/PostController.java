package com.sparta.myblog.controller.ec;

import com.sparta.myblog.dto.ec.WriteCommentDto;
import com.sparta.myblog.service.ec.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post/{postId}/comment")
    public void writeComment(@RequestBody WriteCommentDto dto){

    }
}
