package com.sparta.myblog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecurityService {
    private static String SECRET_KEY = "sdafjalkdjaslkdjaslkdjasdasdasdasd";


    // 로그인 서비스 던질때 같이 실행됨됨
   public String createToken(String subject, long expTime) {
        if(expTime<=0) {
            throw new RuntimeException("만료시간이 0보다 커야합니다,");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // String 형태의 키를 byte로 만들어준다.
        byte[] secretKeybyte = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        // 토큰 키 생성
        Key signinKey = new SecretKeySpec(secretKeybyte, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setSubject(subject) // subject 보통 userID정도 됨
                .signWith(signinKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();
    }

    // 토큰 검증하는 메스드를 boolean타입으로 검증
    public String getSubject(String token) {
        // Claims는 payload에 담긴 정보를 뜻함
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
