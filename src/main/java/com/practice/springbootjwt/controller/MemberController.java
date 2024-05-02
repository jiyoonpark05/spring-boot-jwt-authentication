package com.practice.springbootjwt.controller;

import com.practice.springbootjwt.dto.JwtToken;
import com.practice.springbootjwt.dto.SignInDto;
import com.practice.springbootjwt.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/sign-in")
    public JwtToken signIn(@RequestBody SignInDto signInDto) {
        String loginId = signInDto.getLoginId();
        String username = signInDto.getUsername();
        String password = signInDto.getPassword();


        JwtToken jwtToken = memberService.signIn(loginId,password);
        log.info("request login = {}, password={}" , loginId, password);
        log.info("jwtToken access token = {}, refreshToken = {}", jwtToken.getAccessToken(),jwtToken.getRefreshToken());

        return jwtToken;
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
