package com.sparta.myblog.service;

import com.sparta.myblog.domain.*;
import com.sparta.myblog.dto.*;
import com.sparta.myblog.repository.CommentLikeRepository;
import com.sparta.myblog.repository.PostUserRepository;
import com.sparta.myblog.repository.CommentRepository;
import com.sparta.myblog.repository.PostRepository;
import com.sparta.myblog.repository.ReplyRepository;
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
    private final CommentLikeRepository commentLikeRepository;

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
    public ResponseShCommentDto likeComment(Long commentId, String username) {

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글 입니다.")
        );

        PostUser postUser = postUserRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );

        List<Reply> listReply = replyRepository.findAllByComment(comment);

        int replyCount = listReply.size();
        int likeCount = comment.getLikeCount();
        likeCount++;
        comment.setLikeCount(likeCount);

        Comment updateComment = commentRepository.save(comment);

        CommentLike commentLike = new CommentLike(comment, postUser);
        if(commentLikeRepository.findByComment(comment) == null){
            commentLikeRepository.save(commentLike);
        }

        ResponseShCommentDto responseCommentDto = new ResponseShCommentDto(comment, replyCount);

        return responseCommentDto;
    }

    @Transactional
    public ResponseShCommentDto likeCancelComment(Long commentId, String username) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글 입니다.")
        );

        PostUser postUser = postUserRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );

        List<Reply> listReply = replyRepository.findAllByComment(comment);



        int replyCount = listReply.size();
        int likeCount = comment.getLikeCount();
        likeCount--;
        comment.setLikeCount(likeCount);

        Comment updateComment = commentRepository.save(comment);

        commentLikeRepository.deleteByCommentAndPostUser(comment, postUser);

        ResponseShCommentDto responseCommentDto = new ResponseShCommentDto(comment, replyCount);

        return responseCommentDto;

    }



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

            responseShReplyDtoList = new ArrayList<>();

            //responseShCommentListDto.add(new ResponseShCommentListDto(comment));
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


    public List<CommentLikeDto> findAllMyCommentLike(String username) {
        PostUser postUser = postUserRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );

        List<CommentLike> commentLikems = commentLikeRepository.findAllByPostUser(postUser);
        List<CommentLikeDto> commentLikeDtos = new ArrayList<>();

        for(CommentLike c : commentLikems) {
            commentLikeDtos.add(new CommentLikeDto(c));
        }

        return commentLikeDtos;

    }

    public Long deleteComment(Long commentId, String username){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글 입니다.")
        );

        if(comment.getPostUser().getUsername().equals(username)){
            commentRepository.deleteById(commentId);
        } else{
            throw new IllegalArgumentException("댓글을 쓴 유저가 아닙니다.");
        }
        return commentId;
    }




}
