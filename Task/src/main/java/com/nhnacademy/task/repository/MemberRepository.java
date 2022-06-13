package com.nhnacademy.task.repository;

import com.nhnacademy.task.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    boolean existsByName(String name);
    Optional<Member> findByName(String name);
    @Query("select m from Member as m " +
            "left join projects_members as pm " +
            "on m.memberNo = pm.member.memberNo " +
            "where pm.member.memberNo is null and m.memberNo <> ?1")
    List<Member> findMembersNotInProject(Integer projectNo);

    @Query("select m from Member as m " +
            "inner join projects_members as pm " +
            "on m.memberNo = pm.member.memberNo " +
            "where pm.project.projectNo =?1")
    List<Member> findMemberInProject(Integer projectNo);
}
