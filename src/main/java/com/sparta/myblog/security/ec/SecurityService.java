package com.sparta.myblog.security.ec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.PrivateKey;
import java.util.Date;

@Service
public class SecurityService {
    //시크릿키는 원래 이런 식으로 만들면 안됨!
    private static final String SECRET_KEY = "adfhsaehreghawegawegsdfgawb";

    //로그인 서비스를 던질 때 같이 createToken을 해줌.
    //토큰을 구현하는 메소드 구현. exptime은 만료시간. subject는 아이디.
    public String createToken(String subject, long expTime){
        //만료시간이 0보다 작으면
        if(expTime <= 0){
            throw new RuntimeException("만료시간이 0보다 커야합니다.");
        }
        //서명. 알고리즘을 선택해야한다.
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES256;
        //jaxb-api에서 데이터타입 변환기가 있다. 이걸 이용해서 string형태의 키를 바이트로 만들어준다.
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        //자바 시큐리티에 있는 Key를 임포트하고 Key signingKey라는 변수를 만들어서
        //new SecretKeySpec(바이트로만든 문자열시크릿키, 선택한 알고리즘)을 넣어준다. 이러면 signingKey라는 토큰키가 만들어짐.
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        //만들어진 키를 반환. 실제 아이디는 subject를 쓰고 비밀번호를 받아서 시크릿키를 만드는데 쓴다. 이 방법만 있는건 아님.
        return Jwts.builder()
                .setSubject(subject)
                .signWith(signingKey, signatureAlgorithm)//토큰키와 알고리즘
                //현재시간 + 만료시간
                //만료시간 설정. 헤더값이나 다른 다양한 값들을 넣을 수 있다고 함.
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact(); //최종적으로 String형태의 토큰키가 리턴이 된다.
    }

    //토큰 검증하는 boolean반환 메서드를 만들어서 실제로 토큰을 검증하는 로직에서 메서드를 호출해서 사용하면 된다.
    //클레임은 페이로드 안에 담긴 정보. 토큰 검증
    public String getSubject(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
