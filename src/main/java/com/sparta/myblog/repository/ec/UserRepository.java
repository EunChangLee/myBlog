package com.sparta.myblog.repository.ec;

import com.sparta.myblog.domain.ec.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<PostUser, Long> {
}
