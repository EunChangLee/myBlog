package com.sparta.myblog.service.ec;

import com.sparta.myblog.domain.ec.Comment;
import com.sparta.myblog.domain.ec.Reply;
import com.sparta.myblog.dto.ec.ReplyDto;
import com.sparta.myblog.repository.ec.CommentRepository;
import com.sparta.myblog.repository.ec.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Reply createReply(Long commentId, ReplyDto replyDto){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new IllegalArgumentException("해당 id의 comment가 없습니다.")
        );
        Reply reply = new Reply(comment, replyDto);
        replyRepository.save(reply);
        return reply;
    }

    public List<Reply> readAllReply(){
        return replyRepository.findAll();
    }

    public Reply readReply(Long id){
        return replyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 대댓글을 찾지 못했습니다.")
        );
    }

    @Transactional
    public Reply updateReply(Long id, ReplyDto dto){
        Reply reply = replyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id의 대댓글을 찾지 못했습니다.")
        );
        reply.update(dto);
        return reply;
    }

    @Transactional
    public Long deleteReply(Long id){
        replyRepository.deleteById(id);
        return id;
    }

    @Transactional
    public int likeReply(Long id){
        Reply reply = replyRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("대댓글을 찾지 못했습니다.")
        );
        int likeCount = reply.getLikeCount() + 1;
        reply.setLikeCount(likeCount);
        replyRepository.save(reply);
        return likeCount;
    }
    @Transactional
    public int disLikeReply(Long id){
        Reply reply = replyRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("대댓글을 찾지 못했습니다.")
        );
        int likeCount = reply.getLikeCount() - 1;
        if(likeCount < 0){
            return 0;
        }
        reply.setLikeCount(likeCount);
        replyRepository.save(reply);
        return likeCount;
    }
}
