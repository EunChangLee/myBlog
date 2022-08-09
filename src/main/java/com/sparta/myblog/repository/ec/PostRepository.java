package com.sparta.myblog.repository.ec;

import com.sparta.myblog.domain.ec.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}