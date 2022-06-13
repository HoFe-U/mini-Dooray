package com.nhnacademy.account.service.impl;

import com.nhnacademy.account.data.response.UserIdResponse;
import com.nhnacademy.account.data.response.UserResponse;
import com.nhnacademy.account.data.response.UserLoginResponse;
import com.nhnacademy.account.entity.User;
import com.nhnacademy.account.exception.UserNotFoundException;
import com.nhnacademy.account.repository.UserRepository;
import com.nhnacademy.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserIdResponse> getUserList() {

        List<User> userList = userRepository.findByUserState("가입");

        return userList.stream()
                .map(UserIdResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void userRegister(UserResponse response) {

        if (Objects.nonNull(userRepository.getUserByUserId(response.getId()))) {
            return;
        }

        User user = new User(response.getId(), response.getPw(), response.getEmail(), "가입");
        userRepository.save(user);
    }

    @Override
    public UserLoginResponse findUserById(String id) {
        User user = userRepository.getUserByUserId(id).orElseThrow(UserNotFoundException::new);
        return new UserLoginResponse(user);
    }

    @Override
    public UserLoginResponse findUserByEmail(String email) {
        User user = userRepository.findByUserEmail(email).orElseThrow(UserNotFoundException::new);
        return new UserLoginResponse(user);
    }

    @Transactional
    @Override
    public void userStateModify(String id, String state) {
        User user = userRepository.getUserByUserId(id).orElseThrow(UserNotFoundException::new);

        if (state.equals("탈퇴")) {
            userRepository.deleteById(user.getUserNo());
        } else {
            user.setUserState(state);
            userRepository.save(user);
        }
    }
}
