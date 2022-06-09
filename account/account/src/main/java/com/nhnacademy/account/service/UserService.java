package com.nhnacademy.account.service;

import com.nhnacademy.account.dto.UserDto;
import com.nhnacademy.account.vo.UserRequestVo;
import com.nhnacademy.account.vo.UserResponseVo;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers();

    UserDto getUser(Integer userNo);

    UserResponseVo replaceUser(Integer userNo,UserRequestVo userRequestVo);

    UserDto checkUserIdAndPwd(String userId, String userPwd);

    UserResponseVo createUser(UserRequestVo userRequestVo);

    void removeUser(Integer userNo);

}
