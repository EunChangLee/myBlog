package com.sparta.myblog.repository.ec;

import com.sparta.myblog.domain.ec.Comment;
import com.sparta.myblog.domain.ec.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
}