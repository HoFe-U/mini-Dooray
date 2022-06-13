package com.nhnacademy.task.repository;

import com.nhnacademy.task.entity.ProjectMembers;
import com.nhnacademy.task.entity.pk.ProjectMembersPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMembers, ProjectMembersPk> {
}
