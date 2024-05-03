package com.practice.springbootjwt.dto;

import com.practice.springbootjwt.models.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDto {
    private String loginId;
    private String password;
    private String username;

    private List<String> roles = new ArrayList<>();

    public Member toEntity(String encodedPassword, List<String> roles) {
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .username(username)
                .roles(roles)
                .build();
    }

}
