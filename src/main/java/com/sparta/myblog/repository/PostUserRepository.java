package com.sparta.myblog.repository;


import com.sparta.myblog.domain.PostUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostUserRepository extends JpaRepository<PostUser,Long> {

    Optional<PostUser> findByUsername(String username);

    @EntityGraph(attributePaths = "authorities")
    Optional<PostUser> findOneWithAuthoritiesByUsername(String username);
}
