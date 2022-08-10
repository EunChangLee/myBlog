package com.sparta.myblog.service.sh;

import com.sparta.myblog.domain.ec.*;
import com.sparta.myblog.dto.ec.CommentDto;
import com.sparta.myblog.dto.ec.ResponseCommentDto;
import com.sparta.myblog.dto.sh.*;
import com.sparta.myblog.repository.PostUserRepository;
import com.sparta.myblog.repository.ec.CommentRepository;
import com.sparta.myblog.repository.ec.PostRepository;
import com.sparta.myblog.repository.ec.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShCommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final PostUserRepository postUserRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public ResponseCommentDto saveComment(CommentDto commentDto, Long postId, String username) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다.")
        );

        PostUser postUser = postUserRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );

        Comment comment = new Comment(post,commentDto, postUser);
        comment = commentRepository.save(comment);

        ResponseCommentDto responseCommentDto = new ResponseCommentDto(comment);

        return responseCommentDto;
    }

    @Transactional
    public ResponseShCommentDto likeComment(Long commentId) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글 입니다.")
        );

        List<Reply> listReply = replyRepository.findAllByComment(comment);

        int replyCount = listReply.size();
        int likeCount = comment.getLikeCount();
        likeCount++;
        comment.setLikeCount(likeCount);

        Comment updateComment = commentRepository.save(comment);

        ResponseShCommentDto responseCommentDto = new ResponseShCommentDto(comment, replyCount);

        return responseCommentDto;
    }


    // 다시 해야함
    public  List<ResponseShCommentListDto> findAllComment() {
        List<Comment> commentList = commentRepository.findAll();
        List<ResponseShCommentListDto> responseShCommentListDto = new ArrayList<>();
        List<ResponseShReplyDto> responseShReplyDtoList = new ArrayList<>();

        for (Comment c : commentList) {
            Comment comment = commentRepository.findById(c.getCommentId()).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 댓글입니다.")
            );

            List<Reply> listReply = replyRepository.findAllByComment(comment);

            for(Reply r : listReply) {
                ResponseShReplyDto responseShReplyDto = new ResponseShReplyDto(r);
                responseShReplyDtoList.add(responseShReplyDto);
            }

            responseShCommentListDto.add(new ResponseShCommentListDto(comment, responseShReplyDtoList));

            responseShCommentListDto.add(new ResponseShCommentListDto(comment));
        }


        return responseShCommentListDto;

    }


    public List<ResponseShCommentDto> findAllUserComment(String username) {
        PostUser postUser = postUserRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );
        List<Comment> commentList = commentRepository.findAllByPostUser(postUser);

        List<ResponseShCommentDto> responseShCommentDtoList = new ArrayList<>();

        for(Comment c : commentList) {
            List<Reply> listReply = replyRepository.findAllByComment(c);

            responseShCommentDtoList.add(new ResponseShCommentDto(c, listReply.size()));
        }

        return responseShCommentDtoList;

    }



}
