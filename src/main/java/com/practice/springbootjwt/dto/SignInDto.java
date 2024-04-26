package com.practice.springbootjwt.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {
    private String loginId;
    private String password;


}

