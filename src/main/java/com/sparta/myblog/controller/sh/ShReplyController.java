package com.sparta.myblog.controller.sh;

import com.sparta.myblog.dto.ec.*;
import com.sparta.myblog.dto.sh.ResponseShReplyDto;
import com.sparta.myblog.security.TokenProvider;
import com.sparta.myblog.service.sh.ShReplyService;
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

    @PostMapping("/SaveReply/{commentId}")
    public ResponseEntity<ResponseReplyDto> saveReply(@PathVariable Long commentId, @RequestBody ReplyDto replyDto, ServletRequest servletRequest){

        // 사용자 아이디를 가져오기 위해 헤더에 있는 토큰 값 가져오기
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();


        return ResponseEntity.ok(shReplyService.saveComment(replyDto, commentId, username));
    }

    @PostMapping("/LikeReply/{replyId}")
    public ResponseEntity<ResponseShReplyDto> likePost(@PathVariable Long replyId) {
        return ResponseEntity.ok(shReplyService.likeReply(replyId));
    }

    @PostMapping("/findAllUserReply")
    public ResponseEntity<List<ResponseShReplyDto>> findAllUserReply(ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String bearerToken = httpServletRequest.getHeader("Authorization");
        Authentication authentication= tokenProvider.getAuthentication(bearerToken.substring(7));
        String username = authentication.getName();
        return   ResponseEntity.ok(shReplyService.findAllUserReply(username));
    }
}
