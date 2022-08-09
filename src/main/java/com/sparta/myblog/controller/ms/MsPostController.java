package com.sparta.myblog.controller.ms;

import com.sparta.myblog.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/post/sh")
public class MsPostController {

    @GetMapping("/save")
    public void savePost() {
        System.out.println("save 실행");
    }

    //



}
