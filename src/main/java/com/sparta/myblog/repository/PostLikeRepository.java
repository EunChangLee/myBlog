package com.sparta.myblog.repository;


import com.sparta.myblog.domain.ec.Post;
import com.sparta.myblog.domain.ec.PostLike;
import com.sparta.myblog.domain.ec.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    List<PostLike> findAllByPostUser(PostUser postUser);

    PostLike findByPost(Post post);
}
