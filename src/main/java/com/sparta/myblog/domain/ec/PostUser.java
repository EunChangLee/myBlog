package com.sparta.myblog.domain.ec;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.myblog.domain.Timestamped;
import com.sparta.myblog.dto.ec.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Builder
@Getter
@Setter
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
    private String nickname;

    @JsonIgnore
    @Column(nullable = false)
    private String password;


    public PostUser(String username) {
        this.username = username;
    }
    
    public void update(UserDto dto){
        this.username = dto.getUsername();
        this.nickname = dto.getNickname();
        this.password = dto.getPassword();
    }

    public PostUser(UserDto dto){
        username = dto.getUsername();
        nickname = dto.getNickname();
        password = dto.getPassword();
    }
}
