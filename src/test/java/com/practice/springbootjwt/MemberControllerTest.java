package com.practice.springbootjwt;

import com.practice.springbootjwt.dto.JwtToken;
import com.practice.springbootjwt.dto.MemberDto;
import com.practice.springbootjwt.dto.SignInDto;
import com.practice.springbootjwt.dto.SignUpDto;
import com.practice.springbootjwt.service.MemberService;
import com.practice.springbootjwt.utils.DatabaseCleanup;
import io.jsonwebtoken.lang.Assert;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.assertj.core.api.Assertions;

@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberControllerTest {

    @Autowired
    DatabaseCleanup databaseCleanup;
    @Autowired
    MemberService memberService;
    @Autowired
    TestRestTemplate testRestTemplate;
    @LocalServerPort
    int randomServerPort;

    private SignUpDto signUpDto;

    @BeforeEach
    void beforeEach() {
        // member join
        signUpDto = SignUpDto.builder()
                .loginId("girusu")
                .username("suse")
                .password("1234")
                .build();
    }

    @AfterEach
    void afterEach(){
        databaseCleanup.truncateAllEntity();
    }

    @Test
    public void signUpTest() {
        // set up the API request
        String url =  "http://localhost:" + randomServerPort + "/member/sign-up";
        ResponseEntity<MemberDto> responseEntity = testRestTemplate.postForEntity(url, signUpDto, MemberDto.class);

        // verify the request
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        MemberDto savedMemberDto = responseEntity.getBody();
        Assertions.assertThat(savedMemberDto.getLoginId()).isEqualTo(signUpDto.getLoginId());
       // Assertions.assertThat(savedMemberDto.getUsername()).isEqualTo(signUpDto.getUsername());
    }

    @Test
    public void signInTest() {
        memberService.signUp(signUpDto);

        SignInDto signInDto = SignInDto.builder()
                .loginId("jiyoonius")
                .password("1234")
                .build();

        // request sign in
        JwtToken jwtToken = memberService.signIn(signInDto.getLoginId(),signInDto.getPassword());

        // generate httpHeaders and add token
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(jwtToken.getAccessToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        String url = "http://localhost:" + randomServerPort + "/member/test";
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url, new HttpEntity<>(httpHeaders), String.class);
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo(signInDto.getLoginId());
    }
}
