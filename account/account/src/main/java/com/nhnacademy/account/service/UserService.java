package com.nhnacademy.account.service;

import com.nhnacademy.account.data.response.UserIdResponse;
import com.nhnacademy.account.data.response.UserResponse;
import com.nhnacademy.account.data.response.UserLoginResponse;

import java.util.List;

public interface UserService {

    List<UserIdResponse> getUserList();

    void userRegister(UserResponse response);

    UserLoginResponse findUserById(String id);

    void userStateModify(String id, String state);

    UserLoginResponse findUserByEmail(String email);
}
