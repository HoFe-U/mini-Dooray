package com.nhnacademy.task.repository;

import com.nhnacademy.task.entity.Member;
import com.nhnacademy.task.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> getProjectByMember(Member member);
}
