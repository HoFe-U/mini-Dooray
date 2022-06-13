package com.nhnacademy.account.repository;

import com.nhnacademy.account.entity.User;
import com.nhnacademy.account.exception.NoUserException;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {
    @Autowired
    UserRepository repository;
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .userId("test")
                .userPwd("test")
                .userName("hochul")
                .status("good")
                .email("koohh999@naver.com")
                .build();
    }

    @Test
    void saveTest(){
        User save = repository.save(user);
        assertThat(save).isEqualTo(user);
    }

    @Test
    void findByIdTest(){
        User save = repository.save(user);
        User user1 = repository.findById(save.getUserNo()).orElseThrow();
        assertThat(user1).isEqualTo(user);
    }

    @Test
    void findAllTest(){
        User user1 = User.builder()
                .userId("test1")
                .userPwd("test1")
                .userName("a")
                .status("good")
                .email("a@naver.com")
                .build();

        User user2 = User.builder()
                .userId("test2")
                .userPwd("test2")
                .userName("b")
                .status("good")
                .email("b@naver.com")
                .build();

        repository.saveAll(List.of(user1,user2));

        List<User> all = repository.findAll();

        assertThat(all).isEqualTo(List.of(user1, user2));
    }
    @Test
    void existsByUserIdTest(){
        repository.save(user);
        boolean b = repository.existsByUserId(user.getUserId());
        assertThat(b).isTrue();
    }

    @Test
    void findByUserIdAndUserPwd() {
        repository.save(user);
        User user1 = repository.findByUserIdAndUserPwd(user.getUserId(), user.getUserPwd()).orElseThrow(NoUserException::new);
        assertThat(user1).isEqualTo(user);
    }

    @Test
    void findEmailTest(){
        repository.save(user);

        boolean b = repository.existsByEmail(user.getEmail());

        assertThat(b).isTrue();
    }
}