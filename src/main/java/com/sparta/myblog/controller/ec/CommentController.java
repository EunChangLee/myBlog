package com.sparta.myblog.controller.ec;

import com.sparta.myblog.domain.ec.Comment;
import com.sparta.myblog.dto.ec.CommentDto;
import com.sparta.myblog.dto.ec.ResponseCommentListDto;
import com.sparta.myblog.service.ec.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comment")
    public List<ResponseCommentListDto> readAllComment(){
        return commentService.readAllComment();
    }

    @GetMapping("/comment/{id}")
    public Comment readComment(@PathVariable Long id){
        return commentService.readComment(id);
    }

    @PostMapping("/comment/{postId}")
    public Comment createComment(@PathVariable Long postId, @RequestBody CommentDto dto){
        return commentService.createComment(postId, dto);
    }

    @PutMapping("/comment/{id}")
    public Comment updateComment(@PathVariable Long id, @RequestBody CommentDto dto){
        return commentService.updateComment(id, dto);
    }

    @DeleteMapping("/comment/{id}")
    public Long deleteComment(@PathVariable Long id){
        return commentService.deleteComment(id);
    }

    @PutMapping("/comment/like/{id}")
    public void likeComment(@PathVariable Long id){
        commentService.likeComment(id);
    }

    @PutMapping("/comment/dislike/{id}")
    public void disLikeComment(@PathVariable Long id){
        commentService.disLikeComment(id);
    }
}
