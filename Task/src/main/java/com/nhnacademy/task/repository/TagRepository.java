package com.nhnacademy.task.repository;

import com.nhnacademy.task.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Integer> {
}
