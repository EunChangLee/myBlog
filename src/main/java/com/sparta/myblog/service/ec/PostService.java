package com.sparta.myblog.service.ec;

import com.sparta.myblog.domain.ec.Comment;
import com.sparta.myblog.domain.ec.Post;
import com.sparta.myblog.domain.ec.Reply;
import com.sparta.myblog.dto.ec.PostDto;
import com.sparta.myblog.dto.ec.ResponsePostDto;
import com.sparta.myblog.dto.ec.ResponsePostListDto;
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
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public Post createPost(PostDto dto){
//        PostUser user = userRepository.findById(userId).orElseThrow(
//                ()-> new IllegalArgumentException("해당 id의 유저가 없습니다.")
//        );
        Post post = new Post(dto);
        postRepository.save(post);
        return post;
    }

    public List<ResponsePostListDto> readAllPost(){
        List<ResponsePostListDto> result = new ArrayList<>();
        List<Post> listPost = postRepository.findAll();

        for (Post post : listPost) {
            ResponsePostListDto dto = new ResponsePostListDto();
            List<Comment> commentList = post.getComments();
            dto.setCommentCount(commentList.size());
            dto.setCreatedAt(post.getCreatedAt());
            dto.setModifiedAt(post.getModifiedAt());
            result.add(dto);
        }
        return result;
    }

    public ResponsePostDto readPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        ResponsePostDto result = new ResponsePostDto();

        result.setPostId(post.getPostId());
        result.setPostContent(post.getContent());
        result.setImage(post.getImage());
        result.setTitle(post.getTitle());
        result.setLikeCount(post.getLikeCount());
        //result.setCommentList();

        List<Comment> commentList = commentRepository.findAllByPost(post);

        for(Comment c : commentList){
            ResponsePostDto.Co comment = new ResponsePostDto.Co();
            comment.setContent(c.getContent());
            comment.setLikeCount(c.getLikeCount());
            comment.setCreatedAt(c.getCreatedAt());
            comment.setModifiedAt(c.getModifiedAt());

            List<Reply> replyList = replyRepository.findAllByComment(c);
            for(Reply r : replyList){
                ResponsePostDto.Re reply = new ResponsePostDto.Re();
                reply.setContent(r.getContent());
                reply.setLikeCount(r.getLikeCount());
                reply.setCreatedAt(r.getCreatedAt());
                reply.setModifiedAt(r.getModifiedAt());
                comment.reList.add(reply);
            }
            result.commentList.add(comment);
        }

        return result;
    }

    public Post updatePost(Long id, PostDto dto){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾지 못했습니다.")
        );
        post.update(dto);
        return post;
    }

    public Long deletePost(Long id){
        postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글을 찾지 못했습니다.")
        );
        postRepository.deleteById(id);
        return id;
    }
    @Transactional
    public void likePost(Long id){
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글을 찾지 못했습니다.")
        );
        int likeCount = post.getLikeCount() + 1;
        post.setLikeCount(likeCount);
        postRepository.save(post);
    }

    @Transactional
    public void disLikePost(Long id){
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글을 찾지 못했습니다.")
        );
        int likeCount = post.getLikeCount() - 1;
        if(likeCount < 0){
            return;
        }
        post.setLikeCount(likeCount);
        postRepository.save(post);
    }
}
