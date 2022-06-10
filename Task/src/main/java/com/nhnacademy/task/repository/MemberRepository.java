package com.nhnacademy.task.repository;

import com.nhnacademy.task.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {
}
