package com.sparta.myblog.controller;


import com.sparta.myblog.dto.LoginDto;
import com.sparta.myblog.dto.PostUserDto;
import com.sparta.myblog.dto.ResponseDto;
import com.sparta.myblog.dto.TokenDto;
import com.sparta.myblog.security.JwtFilter;
import com.sparta.myblog.security.TokenProvider;
import com.sparta.myblog.service.ShPostUserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/sh")
public class ShUserController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final ShPostUserService shPostUserService;

    public ShUserController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder
    , ShPostUserService shPostUserService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.shPostUserService = shPostUserService;
    }

   @PostMapping("/signup")
    public ResponseDto<?> signup(@RequestBody @Valid PostUserDto postUserDto) {
        System.out.println("ShUserController signup");
        return shPostUserService.createMember(postUserDto);
    }



    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto loginDto) {
        System.out.println("ShUserController login");

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        //authenticationManagerBuilder이 실행되면 UserDetailsService가 낚아채서 실행된다.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);
        String refresh = tokenProvider.RefreshToken();


//        String jwt = "";
//        String refresh = "";
//
//        try{
//            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            jwt = tokenProvider.createToken(authentication);
//            refresh = tokenProvider.RefreshToken();
//        }catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        httpHeaders.add("refreshToken", refresh);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }



}