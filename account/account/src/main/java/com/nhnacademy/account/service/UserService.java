package com.nhnacademy.account.service;

import com.nhnacademy.account.dto.UserDto;
import com.nhnacademy.account.vo.UserRequestVo;
import com.nhnacademy.account.vo.UserResponseVo;
import com.nhnacademy.account.vo.UserStatusVo;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getUser(String  userId);

    UserResponseVo replaceUser(String userId,UserRequestVo userRequestVo);

    UserResponseVo replaceUser(UserStatusVo userStatusVo);

    UserDto checkUserIdAndPwd(String userId, String userPwd);

    UserResponseVo createUser(UserRequestVo userRequestVo);
    UserResponseVo findEmail(String userEmail);
    void removeUser(String userId);
}
