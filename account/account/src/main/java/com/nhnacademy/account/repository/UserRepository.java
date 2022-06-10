package com.nhnacademy.account.repository;


import com.nhnacademy.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserIdAndUserPwd(String userId, String userPwd);
    boolean existsByUserId(String userId);
    Optional<User> findByUserId(String userId);
    void deleteByUserId(String userid);
    Optional<User> findByEmail(String email);
}
