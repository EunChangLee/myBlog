package com.sparta.myblog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.myblog.dto.ms.msPostUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class PostUser extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;


    public void update(msPostUserDto postUserDto) {
        this.username = postUserDto.getUsername();
        this.password = postUserDto.getPassword();
        this.nickname = postUserDto.getNickname();
    }
}
