package com.nhnacademy.task.repository;

import com.nhnacademy.task.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
