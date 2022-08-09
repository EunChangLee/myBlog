package com.sparta.myblog.service.ec;

import com.sparta.myblog.domain.ec.PostUser;
import com.sparta.myblog.dto.ec.UserDto;
import com.sparta.myblog.repository.ec.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public PostUser createUser(UserDto dto){
        PostUser user = new PostUser(dto);
        return userRepository.save(user);
    }

    public List<PostUser> readAllUser(){
        return userRepository.findAll();
    }

    public PostUser readUser(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("회원을 찾지 못했습니다.")
        );
    }

    @Transactional
    public PostUser updateUser(Long id, UserDto dto){
        PostUser user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("회원을 찾지 못했습니다.")
        );
        user.update(dto);
        return user;
    }

    @Transactional
    public Long deleteUser(Long id){
        userRepository.deleteById(id);
        return id;
    }
}
