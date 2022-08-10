package com.sparta.myblog.controller.sh;

import com.sparta.myblog.domain.ec.Comment;
import com.sparta.myblog.dto.ec.CommentDto;
import com.sparta.myblog.dto.ec.ResponseCommentDto;
import com.sparta.myblog.dto.ec.ResponseCommentListDto;
import com.sparta.myblog.dto.sh.ResponseShCommentDto;
import com.sparta.myblog.dto.sh.ResponseShCommentListDto;
import com.sparta.myblog.dto.sh.ResponseShPostDto;
import com.sparta.myblog.security.TokenProvider;
import com.sparta.myblog.service.sh.ShCommentService;
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
    public ResponseEntity<ResponseShCommentDto> likePost(@PathVariable Long commentId) {
        return ResponseEntity.ok(shCommentService.likeComment(commentId));
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

}
