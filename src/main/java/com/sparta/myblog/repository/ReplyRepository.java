package com.sparta.myblog.repository;

import com.sparta.myblog.domain.Comment;
import com.sparta.myblog.domain.PostUser;
import com.sparta.myblog.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByComment(Comment comment);

    List<Reply> findAllByPostUser(PostUser postUser);
}
