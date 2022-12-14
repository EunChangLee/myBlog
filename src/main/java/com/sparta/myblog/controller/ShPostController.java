package com.sparta.myblog.controller;

import com.sparta.myblog.domain.Post;
import com.sparta.myblog.dto.PostDto;
import com.sparta.myblog.dto.PostLikeDto;
import com.sparta.myblog.dto.ResponseShPostDto;
import com.sparta.myblog.security.TokenProvider;
import com.sparta.myblog.service.ShPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/sh")
@RequiredArgsConstructor
public class ShPostController {

    private final ShPostService shPostService;
    private final TokenProvider tokenProvider;


    // 게시글 저장
    @PostMapping("/SavePost")
    public ResponseEntity<Post> savePost(@RequestBody PostDto postDto, ServletRequest servletRequest){
        // 사용자 아이디를 가져오기 위해 헤더에 있는 토큰 값 가져오기
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();

        return ResponseEntity.ok(shPostService.savePost(postDto, username));
    }

    // 게시글 좋아요
    @PostMapping("/LikePost/{id}")
    public ResponseEntity<ResponseShPostDto> likePost(@PathVariable Long id, ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();

        return ResponseEntity.ok(shPostService.likePost(id, username));
    }

    // 게시글 좋아요 취소
    @PostMapping("/LikeCancelPost/{id}")
    public ResponseEntity<ResponseShPostDto> likeCancelPost(@PathVariable Long id, ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();

        return ResponseEntity.ok(shPostService.likeCancelPost(id, username));
    }

    // 게시글 전체 조회
    @GetMapping("/findAllPost")
    public ResponseEntity<List<ResponseShPostDto>> findAllPost(){
        return  ResponseEntity.ok(shPostService.findAllPost());
    }

    @GetMapping("/findOnePost/{id}")
    public ResponseEntity<ResponseShPostDto> findOnePost(@PathVariable Long id){
        return  ResponseEntity.ok(shPostService.findOnePost(id));
    }


    // 사용자가 쓴 게시글
    @PostMapping("/findAllUserPost")
    public ResponseEntity<List<ResponseShPostDto>> findAllUserPost(ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();
        return   ResponseEntity.ok(shPostService.findAllUserPost(username));
    }


    // 사용자가 좋아요 누른 게시글
    @PostMapping("/findAllMyPostLike")
    public ResponseEntity<List<PostLikeDto>> findAllMyPostLike(ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();

        return   ResponseEntity.ok(shPostService.findAllMyPostLike(username));
    }

    // 게시글 삭제
    @DeleteMapping("/deletePost/{id}")
    public String deletePost(@PathVariable Long id, ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();

        Long result = shPostService.deletePost(id, username);

        return  result + "번 게시물이 삭제되었습니다.";
    }


}
