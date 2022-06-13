package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.data.request.UserRegisterRequest;
import com.nhnacademy.gateway.data.response.UserIdResponse;
import com.nhnacademy.gateway.data.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse getUser(String id);

    void registerUser(UserRegisterRequest request);

    void modifyUserState(String user, String state);

    UserResponse findByUserEmail(String email);
}
