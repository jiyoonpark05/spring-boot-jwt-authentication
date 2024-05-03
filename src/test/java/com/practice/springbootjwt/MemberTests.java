package com.practice.springbootjwt;

import com.practice.springbootjwt.models.Member;
import com.practice.springbootjwt.repositories.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class MemberTests {
    @Autowired
    MemberRepository memberRepository;


    // create user
    @Test
    void saveMember() {
        Member params = Member.builder()
                .loginId("jiyoonius")
                .password("1234")
                .username("Jiyoon")
                .build();
        memberRepository.save(params);
    }

    @Test
    void findAllMember() {
        memberRepository.findAll();
    }

    @Test
    void findMemberById() {
        Member member = memberRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException());
        Assertions.assertEquals(member.getLoginId(), "jiyoonius");
    }

    @Test
    void deleteMemberById() {
        memberRepository.deleteById(1L);
    }

}
