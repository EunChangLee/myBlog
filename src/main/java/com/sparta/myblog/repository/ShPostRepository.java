package com.sparta.myblog.repository;


import com.sparta.myblog.domain.ec.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShPostRepository extends JpaRepository<Post, Long> {
}
