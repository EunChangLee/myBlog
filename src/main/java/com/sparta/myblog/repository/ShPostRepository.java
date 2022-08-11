package com.sparta.myblog.repository;


import com.sparta.myblog.domain.Post;
import com.sparta.myblog.domain.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShPostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByPostUser(PostUser postUser);
}
