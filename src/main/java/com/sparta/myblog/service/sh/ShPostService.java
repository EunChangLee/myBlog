package com.sparta.myblog.service.sh;


import com.sparta.myblog.domain.ec.Comment;
import com.sparta.myblog.domain.ec.Post;
import com.sparta.myblog.domain.ec.PostLike;
import com.sparta.myblog.domain.ec.PostUser;
import com.sparta.myblog.dto.ec.PostDto;
import com.sparta.myblog.dto.sh.PostLikeDto;
import com.sparta.myblog.dto.sh.ResponseShPostDto;
import com.sparta.myblog.repository.PostLikeRepository;
import com.sparta.myblog.repository.PostUserRepository;
import com.sparta.myblog.repository.ShPostRepository;
import com.sparta.myblog.repository.ec.CommentRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ShPostService {

    private final ShPostRepository shPostRepository;
    private final PostUserRepository postUserRepository;

    private final CommentRepository commentRepository;
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public Post savePost(PostDto postDto, String username) {
        PostUser postUser = postUserRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );
        Post post = new Post(postDto, postUser);
        return shPostRepository.save(post);
    }

    @Transactional
    public ResponseShPostDto likePost(Long id, String username) {
        Post post = shPostRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );

        PostUser postUser = postUserRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );

        List<Comment> listComment = commentRepository.findAllByPost(post);

        int commentCount = listComment.size();
        int likeCount = post.getLikeCount();
        likeCount++;
        post.setLikeCount(likeCount);
        Post updatePost = shPostRepository.save(post);

        PostLike postLike = new PostLike(post, postUser);
        if(postLikeRepository.findByPost(post) == null){
            postLikeRepository.save(postLike);
        }


        //PostLikeDto postLikeDto = new PostLikeDto(postLike.getPost().getPostId(), postLike.getPostUser().getUsername());

        ResponseShPostDto responseShPostDto = new ResponseShPostDto(updatePost, commentCount);

        return responseShPostDto;
    }

    @Transactional
    public ResponseShPostDto likeCancelPost(Long id, String username) {
        Post post = shPostRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );

        PostUser postUser = postUserRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );

        List<Comment> listComment = commentRepository.findAllByPost(post);

        int commentCount = listComment.size();
        int likeCount = post.getLikeCount();
        likeCount--;
        post.setLikeCount(likeCount);
        Post updatePost = shPostRepository.save(post);

        postLikeRepository.deleteByPostAndPostUser(post, postUser);

        ResponseShPostDto responseShPostDto = new ResponseShPostDto(updatePost, commentCount);

        return responseShPostDto;

    }




    public  List<ResponseShPostDto> findAllPost() {
        List<Post> postList = shPostRepository.findAll();
        List<ResponseShPostDto> responseShPostDtoList = new ArrayList<>();

        for (Post p : postList) {
            Post post = shPostRepository.findById(p.getPostId()).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
            );

            List<Comment> listComment = commentRepository.findAllByPost(post);

            responseShPostDtoList.add( new ResponseShPostDto(p,listComment.size()));
        }

        return responseShPostDtoList;

    }

    public ResponseShPostDto findOnePost(Long id) {
        Post post = shPostRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );

        List<Comment> listComment = commentRepository.findAllByPost(post);

        ResponseShPostDto responseShPostDto = new ResponseShPostDto(post,listComment.size());
        return responseShPostDto;
    }

    public List<ResponseShPostDto> findAllUserPost(String username) {
        PostUser postUser = postUserRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );
        List<Post> postList = shPostRepository.findAllByPostUser(postUser);

        List<ResponseShPostDto> responseShPostDtoList = new ArrayList<>();

        for(Post p : postList) {
            List<Comment> listComment = commentRepository.findAllByPost(p);

            responseShPostDtoList.add(new ResponseShPostDto(p, listComment.size()));
        }

        return responseShPostDtoList;

    }

    public List<PostLikeDto> findAllMyPostLike(String username) {
        PostUser postUser = postUserRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("유저가 존재하지 않습니다.")
        );

        List<PostLike> postLikeList = postLikeRepository.findAllByPostUser(postUser);
        List<PostLikeDto> postLikeDtos = new ArrayList<>();

        for(PostLike p : postLikeList) {
            postLikeDtos.add(new PostLikeDto(p));
        }

        return postLikeDtos;

    }


    public Long deletePost(Long id, String username){
         Post post = shPostRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글을 찾지 못했습니다.")
        );

        if(post.getPostUser().getUsername().equals(username)){
            shPostRepository.deleteById(id);
        } else{
            throw new IllegalArgumentException("게시글을 쓴 유저가 아닙니다.");
        }
        return id;
    }


}
