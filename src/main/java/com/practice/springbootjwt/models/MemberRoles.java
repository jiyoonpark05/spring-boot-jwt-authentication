package com.practice.springbootjwt.models;

import jakarta.persistence.*;
import lombok.*;

import java.lang.annotation.Documented;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRoles {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String role;

}
