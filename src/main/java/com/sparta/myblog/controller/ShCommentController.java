package com.sparta.myblog.controller;

import com.sparta.myblog.dto.*;
import com.sparta.myblog.security.TokenProvider;
import com.sparta.myblog.service.ShCommentService;
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
public class ShCommentController {

    private final ShCommentService shCommentService;

    private final TokenProvider tokenProvider;

    // 댓글 저장
    @PostMapping("/SaveComment/{postId}")
    public ResponseEntity<ResponseCommentDto> saveComment(@PathVariable Long postId, @RequestBody CommentDto comment, ServletRequest servletRequest){
        // 사용자 아이디를 가져오기 위해 헤더에 있는 토큰 값 가져오기
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();

        return ResponseEntity.ok(shCommentService.saveComment(comment, postId, username));
    }

    // 댓글 좋아요
    @PostMapping("/LikeComment/{commentId}")
    public ResponseEntity<ResponseShCommentDto> likePost(@PathVariable Long commentId, ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();

        return ResponseEntity.ok(shCommentService.likeComment(commentId, username));
    }

    // 댓글 좋아요 취소
    @PostMapping("/LikeCancelComment/{id}")
    public ResponseEntity<ResponseShCommentDto> likeCancelComment(@PathVariable Long id, ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();

        return ResponseEntity.ok(shCommentService.likeCancelComment(id, username));
    }

    // 댓글 전체조회
    @GetMapping("/findAllComment")
    public ResponseEntity<List<ResponseShCommentListDto>> findAllComment() {
        return  ResponseEntity.ok(shCommentService.findAllComment());
    }

    // 사용자가 쓴 댓글
    @PostMapping("/findAllUserComment")
    public ResponseEntity<List<ResponseShCommentDto>> findAllUserComment(ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();
        return   ResponseEntity.ok(shCommentService.findAllUserComment(username));
    }

    // 사용자가 좋아요 누른 댓글
    @PostMapping("/findAllMyCommentLike")
    public ResponseEntity<List<CommentLikeDto>> findAllMyPostLike(ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();

        return   ResponseEntity.ok(shCommentService.findAllMyCommentLike(username));
    }

    // 댓글 삭제
    @DeleteMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable Long id, ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();

        Long result = shCommentService.deleteComment(id, username);

        return  result + "번 댓글이 삭제되었습니다.";
    }


}
