package com.sparta.myblog.controller.ec;

import com.sparta.myblog.domain.ec.Post;
import com.sparta.myblog.dto.ec.PostDto;
import com.sparta.myblog.dto.ec.ResponsePostDto;
import com.sparta.myblog.dto.ec.ResponsePostListDto;
import com.sparta.myblog.service.ec.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post/{userId}")
    public Post createPost(@PathVariable Long userId, @RequestBody PostDto dto){

        return postService.createPost(dto);
    }
    @GetMapping("/post")
    public List<ResponsePostListDto> readAllPost(){
        return postService.readAllPost();
    }
    @GetMapping("/post/{id}")
    public ResponsePostDto readPost(@PathVariable Long id){
        return postService.readPost(id);
    }
    @PutMapping("/post/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostDto dto){
        return postService.updatePost(id, dto);
    }

    @DeleteMapping("/post/{id}")
    public Long deletePost(@PathVariable Long id){
        return postService.deletePost(id);
    }

    @PutMapping("/post/like/{id}")
    public void likePost(@PathVariable Long id){
        postService.likePost(id);
    }

    @PutMapping("/post/dislike/{id}")
    public void disLikePost(@PathVariable Long id){
        postService.disLikePost(id);
    }
}
