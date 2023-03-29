package com.study.springdatajpa.repository;

import com.study.springdatajpa.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@Transactional
@Rollback(false)
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberJpaRepository;
    @Test
    public void testMember() {
        Member member = new Member("memberA");
        Member savedMember = memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.findById(savedMember.getId()).get();
        assertTrue(findMember.getId().equals(member.getId()));
        assertTrue(findMember.getUsername().equals(member.getUsername()));
        assertTrue(findMember.equals(member)); //JPA 엔티티 동일성 보장

    }
}