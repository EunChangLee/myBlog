package com.sparta.myblog.security.ec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/security")
public class SecurityController {
    //의존성 설정
    @Autowired
    private SecurityService securityService;

    //키가 json형태니까 Object로
    //원래는 subject가 아이디고 비밀번호를 키값으로 같이 주는 POST로 해야한다.
    @GetMapping("/create/token")
    public Map<String, Object> createToken(@RequestParam(value = "subject") String subject){
        String token = securityService.createToken(subject, (2*1000*60)); //테스트를 위해 만료시간을 짧게 2분으로
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", token);
        return map;
    }

    //토큰을 검증한다. 정보들이 담겨있는 claims라는걸 만든다.
    @GetMapping("/get/subject")
    public Map<String, Object> getSubject(@RequestParam(value = "token") String token){
        String subject = securityService.getSubject(token);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", subject);
        return map;
    }
}
