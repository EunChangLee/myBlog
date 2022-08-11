package com.sparta.myblog.service;


import com.sparta.myblog.domain.PostUser;
import com.sparta.myblog.dto.PostUserDto;
import com.sparta.myblog.dto.ResponseDto;
import com.sparta.myblog.repository.PostUserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ShPostUserService {

    private final PostUserRepository postUserRepository;

    private final PasswordEncoder passwordEncoder;

    // 회원가입 로직
    @Transactional
    public ResponseDto<?> createMember(PostUserDto postUserDto) {
        if (null != isPresentMember(postUserDto.getUserName())) {
            return ResponseDto.fail("DUPLICATED_NICKNAME",
                    "중복된 아이디 입니다.");
        }

        PostUser postUser = PostUser.builder()
                .username(postUserDto.getUserName())
                .nickname(postUserDto.getNickName())
                .password(passwordEncoder.encode(postUserDto.getPassword()))
                .build();

        postUserRepository.save(postUser);

        return ResponseDto.success(
                PostUserDto.builder()
                        .id(postUser.getUserId())
                        .nickName(postUser.getNickname())
                        .userName(postUser.getUsername())
                        .password(postUser.getPassword())
                        .createdAt(postUser.getCreatedAt())
                        .modifiedAt(postUser.getModifiedAt())
                        .build()
        );
    }

    // 닉네임으로 유저 아이디 찾기
    @Transactional(readOnly = true)
    public PostUser isPresentMember(String userName) {
        Optional<PostUser> optionalMember = postUserRepository.findByUsername(userName);
        return optionalMember.orElse(null);
    }



}