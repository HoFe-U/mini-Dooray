package com.nhnacademy.account.repository;

import com.nhnacademy.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getUserByUserId(String id);

    List<User> findByUserState(String status);
    Optional<User> findByUserEmail(String email);
}
