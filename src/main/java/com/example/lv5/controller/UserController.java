package com.example.lv5.controller;

import com.example.lv5.dto.SignupUserRequestDto;
import com.example.lv5.dto.StatusResponseDto;
import com.example.lv5.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    //회원가입 구현
    @PostMapping("/auth/signup")
    public StatusResponseDto signup(@RequestBody @Valid SignupUserRequestDto signupUserRequestDto) {
        return userService.signup(signupUserRequestDto);
    }

}
