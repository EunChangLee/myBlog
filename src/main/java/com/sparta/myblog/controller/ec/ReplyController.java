package com.sparta.myblog.controller.ec;

import com.sparta.myblog.domain.ec.Reply;
import com.sparta.myblog.dto.ec.ReplyDto;
import com.sparta.myblog.service.ec.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @GetMapping("/reply")
    public List<Reply> readAllComment(){
        return replyService.readAllReply();
    }

    @GetMapping("/reply/{id}")
    public Reply readReply(@PathVariable Long id){
        return replyService.readReply(id);
    }

    @PostMapping("/reply/{commentId}")
    public Reply createReply(@PathVariable Long commentId, @RequestBody ReplyDto dto){
        return replyService.createReply(commentId, dto);
    }

    @PutMapping("/reply/{id}")
    public Reply updateReply(@PathVariable Long id, @RequestBody ReplyDto dto){
        return replyService.updateReply(id, dto);
    }

    @DeleteMapping("/reply/{id}")
    public Long deleteReply(@PathVariable Long id){
        return replyService.deleteReply(id);
    }

    @PutMapping("/reply/like/{id}")
    public void likeReply(@PathVariable Long id){
        replyService.likeReply(id);
    }

    @PutMapping("/reply/dislike/{id}")
    public void disLikeReply(@PathVariable Long id){
        replyService.disLikeReply(id);
    }
}
