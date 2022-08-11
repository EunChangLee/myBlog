package com.sparta.myblog.controller;

import com.sparta.myblog.dto.ReplyDto;
import com.sparta.myblog.dto.ResponseReplyDto;
import com.sparta.myblog.dto.ResponseShReplyDto;
import com.sparta.myblog.security.TokenProvider;
import com.sparta.myblog.service.ShReplyService;
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
public class ShReplyController {

    private final TokenProvider tokenProvider;

    private final ShReplyService shReplyService;

    // 대댓글 저장
    @PostMapping("/SaveReply/{commentId}")
    public ResponseEntity<ResponseReplyDto> saveReply(@PathVariable Long commentId, @RequestBody ReplyDto replyDto, ServletRequest servletRequest){

        // 사용자 아이디를 가져오기 위해 헤더에 있는 토큰 값 가져오기
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();


        return ResponseEntity.ok(shReplyService.saveComment(replyDto, commentId, username));
    }

    // 대댓글 좋아요
    @PostMapping("/LikeReply/{replyId}")
    public ResponseEntity<ResponseShReplyDto> likePost(@PathVariable Long replyId) {
        return ResponseEntity.ok(shReplyService.likeReply(replyId));
    }

    // 대댓글 좋아요 취소
    @PostMapping("/LikeCancelReply/{id}")
    public ResponseEntity<ResponseShReplyDto> likeCancelReply(@PathVariable Long id, ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();

        return ResponseEntity.ok(shReplyService.likeCancelReply(id));
    }


    // 내가쓴 대댓글 찾기
    @PostMapping("/findAllUserReply")
    public ResponseEntity<List<ResponseShReplyDto>> findAllUserReply(ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();
        return   ResponseEntity.ok(shReplyService.findAllUserReply(username));
    }

    // 대댓글 삭제
    @DeleteMapping("/deleteReply/{id}")
    public String deleteReply(@PathVariable Long id, ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();

        Long result = shReplyService.deleteReply(id, username);

        return  result + "번 게시물이 삭제되었습니다.";
    }
}
