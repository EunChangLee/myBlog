package com.sparta.myblog.controller.ec;

import com.sparta.myblog.domain.ec.PostUser;
import com.sparta.myblog.dto.ec.UserDto;
import com.sparta.myblog.service.ec.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public List<PostUser> readAllUser(){
        return userService.readAllUser();
    }

    @GetMapping("/user/{id}")
    public PostUser readUser(@PathVariable Long id){
        return userService.readUser(id);
    }

    @PostMapping("/user")
    public PostUser createUser(@RequestBody UserDto dto){
        return userService.createUser(dto);
    }

    @PutMapping("/user/{id}")
    public PostUser updateUser(@PathVariable Long id, @RequestBody UserDto dto){
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/user/{id}")
    public Long deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
