package com.practice.springbootjwt.repositories;

import com.practice.springbootjwt.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
