package com.study.springdatajpa.repository;

import com.study.springdatajpa.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(false)
public class MemberJpaRepositoryTest {
    @Autowired
    MemberJpaRepository memberJpaRepository;
    @Test
    public void testMember() {
        Member member = new Member("memberA");
        Member savedMember = memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.find(savedMember.getId());
        assertTrue(findMember.getId().equals(member.getId()));
        assertTrue(findMember.getUsername().equals(member.getUsername()));
        assertTrue(findMember.equals(member)); //JPA 엔티티 동일성 보장
        System.out.println("엥");

    }

    @Test
    public void findByUsernameAndAgeGreaterThan() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);
        List<Member> result =
                memberJpaRepository.findByUsernameAndAgeGreaterThan("AAA", 15);
        assertTrue(result.get(0).getUsername().equals("AAA"));
        assertTrue(result.get(0).getAge()==(20));
        assertEquals(result.size(),1);
    }
}