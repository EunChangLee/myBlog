package com.sparta.myblog.repository;


import com.sparta.myblog.domain.Comment;
import com.sparta.myblog.domain.CommentLike;
import com.sparta.myblog.domain.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    List<CommentLike> findAllByPostUser(PostUser postUser);

    void deleteByCommentAndPostUser(Comment comment, PostUser postUser);

    CommentLike findByComment(Comment comment);
}
