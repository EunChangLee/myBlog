package com.sparta.myblog.service.sh;

import com.sparta.myblog.domain.ec.Comment;
import com.sparta.myblog.domain.ec.Post;
import com.sparta.myblog.domain.ec.PostUser;
import com.sparta.myblog.domain.ec.Reply;
import com.sparta.myblog.dto.ec.ReplyDto;
import com.sparta.myblog.dto.ec.ResponseReplyDto;
import com.sparta.myblog.dto.sh.ResponseShCommentDto;
import com.sparta.myblog.dto.sh.ResponseShReplyDto;
import com.sparta.myblog.repository.PostUserRepository;
import com.sparta.myblog.repository.ec.CommentRepository;
import com.sparta.myblog.repository.ec.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShReplyService {

    private final PostUserRepository postUserRepository;
    private final CommentRepository commentRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public ResponseReplyDto saveComment(ReplyDto replyDto, Long commentId, String username) {


        PostUser postUser = postUserRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글 입니다.")
        );

        Reply reply = new Reply(comment, replyDto, postUser);
        reply = replyRepository.save(reply);

        ResponseReplyDto responseReplyDto = new ResponseReplyDto(reply);


        return responseReplyDto;
    }

    @Transactional
    public ResponseShReplyDto likeReply(Long commentId) {

        Reply reply = replyRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 대댓글 입니다.")
        );

        int likeCount = reply.getLikeCount();
        likeCount++;
        reply.setLikeCount(likeCount);

        reply = replyRepository.save(reply);

        ResponseShReplyDto responseShReplyDto = new ResponseShReplyDto(reply);

        return responseShReplyDto;
    }

    @Transactional
    public ResponseShReplyDto likeCancelReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 대댓글 입니다.")
        );

        int likeCount = reply.getLikeCount();
        likeCount--;
        reply.setLikeCount(likeCount);

        reply = replyRepository.save(reply);

        ResponseShReplyDto responseShReplyDto = new ResponseShReplyDto(reply);

        return responseShReplyDto;

    }


    public List<ResponseShReplyDto> findAllUserReply(String username) {
        PostUser postUser = postUserRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );
        List<Reply> replyList = replyRepository.findAllByPostUser(postUser);

        List<ResponseShReplyDto> responseShReplyDtoList = new ArrayList<>();

        for(Reply r : replyList) {

            responseShReplyDtoList.add(new ResponseShReplyDto(r));
        }

        return responseShReplyDtoList;

    }

    public Long deleteReply(Long id, String username){
        Reply reply = replyRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("대댓글을 찾지 못했습니다.")
        );

        if(reply.getPostUser().getUsername().equals(username)){
            replyRepository.deleteById(id);
        } else{
            throw new IllegalArgumentException("대댓글을 쓴 유저가 아닙니다.");
        }
        return id;
    }
}
