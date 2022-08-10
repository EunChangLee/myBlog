package com.sparta.myblog.repository;


import com.sparta.myblog.domain.ec.Post;
import com.sparta.myblog.domain.ec.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShPostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByPostUser(PostUser postUser);
}
