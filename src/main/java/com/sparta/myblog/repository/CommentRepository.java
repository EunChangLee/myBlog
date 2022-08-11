package com.sparta.myblog.repository;

import com.sparta.myblog.domain.Comment;
import com.sparta.myblog.domain.Post;
import com.sparta.myblog.domain.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);

    List<Comment> findAllByPostUser(PostUser postUser);
}