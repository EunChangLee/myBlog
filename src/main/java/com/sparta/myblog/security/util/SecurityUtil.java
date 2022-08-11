package com.sparta.myblog.security.util;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
@NoArgsConstructor
public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

//    private SecurityUtil() {
//    }

    // Security Context의 Authenticcation 객체를 이용해 username을 리턴해주는 유틸성 메소드
    // JwtFilter에 doFilter메소드에서 Authentication객체를 저장함 그래서 그 부분을 여기서 getContext로 꺼내올 수 있음
    public static Optional<String> getCurrentUsername() {


        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }


        String userName = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            userName = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            userName = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(userName);
    }
}
