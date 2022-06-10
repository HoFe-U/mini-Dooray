package com.nhnacademy.task.repository;

import com.nhnacademy.task.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone,Integer> {
}
