package com.sparta.myblog.service.ec;

import com.sparta.myblog.domain.ec.Comment;
import com.sparta.myblog.domain.ec.Post;
import com.sparta.myblog.dto.ec.CommentDto;
import com.sparta.myblog.dto.ec.ResponseCommentListDto;
import com.sparta.myblog.repository.ec.CommentRepository;
import com.sparta.myblog.repository.ec.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public Comment createComment(Long postId, CommentDto dto){
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new IllegalArgumentException("해당 id의 post가 없습니다.")
        );
        Comment comment = new Comment(dto);
        comment.setPost(post);
        commentRepository.save(comment);

        return comment;
    }

    public List<ResponseCommentListDto> readAllComment(){
        List<Comment> commentList = commentRepository.findAll();
        List<ResponseCommentListDto> cDtoList = new ArrayList<>();


        for(Comment c : commentList){
            ResponseCommentListDto dto = new ResponseCommentListDto();

            dto.setId(c.getCommentId());
            dto.setContent(c.getContent());
            dto.setLikeCount(c.getLikeCount());
            dto.setReplyList(c.getReplies());
            cDtoList.add(dto);
        }

        return cDtoList;
    }
    public Comment readComment(Long id){
        return commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾지 못했습니다.")
        );
    }
    @Transactional
    public Comment updateComment(Long id, CommentDto dto){
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾지 못했습니다.")
        );
        comment.update(dto);
        return comment;
    }

    @Transactional
    public Long deleteComment(Long id){
        commentRepository.deleteById(id);
        return id;
    }
    @Transactional
    public void likeComment(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("댓글을 찾지 못했습니다.")
        );
        int likeCount = comment.getLikeCount() + 1;
        comment.setLikeCount(likeCount);
        commentRepository.save(comment);
    }
    @Transactional
    public void disLikeComment(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("댓글을 찾지 못했습니다.")
        );
        int likeCount = comment.getLikeCount() - 1;
        if(likeCount < 0){
            return;
        }
        comment.setLikeCount(likeCount);
        commentRepository.save(comment);
    }
}
