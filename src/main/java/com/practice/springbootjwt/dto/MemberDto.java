package com.practice.springbootjwt.dto;

import com.practice.springbootjwt.models.Member;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private Long id;
    private String loginId;
    private String username;

    static public MemberDto toDto(Member member){
        return MemberDto.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .username(member.getUsername())
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .loginId(loginId)
                .username(username)
                .build();
    }
}
