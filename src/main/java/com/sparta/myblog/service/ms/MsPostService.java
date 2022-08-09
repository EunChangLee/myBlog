package com.sparta.myblog.service.ms;

import com.sparta.myblog.Repository.msRepository.msPostUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MsPostService {
    private final msPostUserRepository mspostUserRepository;

}
