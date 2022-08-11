package com.sparta.myblog.repository;


import com.sparta.myblog.domain.Post;
import com.sparta.myblog.domain.PostLike;
import com.sparta.myblog.domain.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    List<PostLike> findAllByPostUser(PostUser postUser);

    void deleteByPostAndPostUser(Post post, PostUser postUser);

    PostLike findByPost(Post post);
}
