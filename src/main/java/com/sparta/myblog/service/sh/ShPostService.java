package com.sparta.myblog.service.sh;


import com.sparta.myblog.repository.ShPostRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ShPostService {

    private final ShPostRepository shPostRepository;


}
