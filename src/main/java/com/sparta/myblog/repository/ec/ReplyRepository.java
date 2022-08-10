package com.sparta.myblog.repository.ec;

import com.sparta.myblog.domain.ec.Comment;
import com.sparta.myblog.domain.ec.PostUser;
import com.sparta.myblog.domain.ec.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByComment(Comment comment);

    List<Reply> findAllByPostUser(PostUser postUser);
}
