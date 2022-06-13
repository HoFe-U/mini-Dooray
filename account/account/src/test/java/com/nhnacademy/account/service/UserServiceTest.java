package com.nhnacademy.account.service;

import com.nhnacademy.account.dto.UserDto;
import com.nhnacademy.account.entity.User;
import com.nhnacademy.account.exception.NoUserException;
import com.nhnacademy.account.exception.UserIdOverlapException;
import com.nhnacademy.account.repository.UserRepository;
import com.nhnacademy.account.vo.UserRequestVo;
import com.nhnacademy.account.vo.UserResponseVo;
import com.nhnacademy.account.vo.UserStatusVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User user1;

    @BeforeEach
    void setUp() {
        user1 = User.builder()
                .userId("a")
                .userPwd("a")
                .userName("b")
                .status("good")
                .email("koohh999@naver.com")
                .build();
    }
    @Test
    void getUsersException(){
        assertThrows(NoUserException.class, () -> userService.getUsers());
    }
    @Test
    @Rollback
    void getUsers() {
    User user2 = User.builder()
                .userId("b")
                .userPwd("b")
                .userName("b")
                .status("good")
                .email("b@naver.com")
                .build();

        userRepository.saveAll(List.of(user1, user2));
        List<UserDto> users = userService.getUsers();

        UserDto userDto = users.get(0);
        UserDto userDto1 = users.get(1);

        assertThat(userDto.getUserId()).isEqualTo(user1.getUserId());
        assertThat(userDto1.getUserId()).isEqualTo(user2.getUserId());
    }

    @Test
    void getUser() {
        userRepository.save(user1);

        UserDto user = userService.getUser("a");

        assertThat(user1.getUserId()).isEqualTo(user.getUserId());
    }

    @Test
    void replaceUser() {
        userRepository.save(user1);
        UserRequestVo build = UserRequestVo.builder()
                .id("c")
                .pwd("c")
                .name("c")
                .status("RETIRE")
                .email("ttt@naver.com")
                .build();

        UserResponseVo userResponseVo = userService.replaceUser(user1.getUserId(), build);

        assertThat(userResponseVo.getUserNo()).isEqualTo(user1.getUserNo());
    }

    @Test
    void checkUserIdAndPwd() {
        userRepository.save(user1);

        UserDto userDto = userService.checkUserIdAndPwd(user1.getUserId(), user1.getUserPwd());

        assertThat(user1.getUserId()).isEqualTo(userDto.getUserId());
    }
    @Test
    void createUserException(){
        userRepository.save(user1);

        UserRequestVo build = UserRequestVo.builder()
                .id("a")
                .pwd("a")
                .name("a")
                .email("email@naver.com")
                .status("good")
                .build();

        assertThrows(UserIdOverlapException.class, () -> userService.createUser(build));

    }

    @Test
    void createUserTest() {
        UserRequestVo build = UserRequestVo.builder()
                .id("a")
                .pwd("a")
                .name("a")
                .email("email@naver.com")
                .status("good")
                .build();
        UserResponseVo user = userService.createUser(build);

        User user2 = userRepository.findByUserId(build.getId()).orElseThrow(NoUserException::new);

        assertThat(user2.getUserId()).isEqualTo(build.getId());
    }

    @Test
    void removeUser() {
        userRepository.save(user1);
        userService.removeUser(user1.getUserId());
        assertThrows(NoUserException.class, () -> userService.getUser(user1.getUserId()));
    }

    @Test
    void replaceUserStatusTest(){
        userRepository.save(user1);
        UserStatusVo userStatusVo = new UserStatusVo("a", "bad");

        userService.replaceUser(userStatusVo);
        assertThat(user1.getStatus()).isEqualTo(userStatusVo.getStatus());
    }

    @Test
    void findUserByEmailTest(){
        userRepository.save(user1);

        boolean email = userService.checkEmail(user1.getEmail());

        assertThat(email).isTrue();
    }
}