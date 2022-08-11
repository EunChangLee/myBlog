package com.sparta.myblog.service;


import com.sparta.myblog.domain.UserDetailsImpl;
import com.sparta.myblog.domain.PostUser;
import com.sparta.myblog.repository.PostUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final PostUserRepository postUserRepository;

    public CustomUserDetailsService(PostUserRepository postUserRepository) {
        this.postUserRepository = postUserRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        System.out.println("CustomUserDetailsService loadUserByUsername");
        Optional<PostUser> postUser = postUserRepository.findByUsername(username);

        return postUser
                .map(UserDetailsImpl::new)
                .orElseThrow(()-> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

}
