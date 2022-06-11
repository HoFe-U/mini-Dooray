package com.nhnacademy.account.dto;

import com.nhnacademy.account.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserDto {
    private String userId;
    private String userPwd;
    private String userEmail;
    private String userStatus;


    public UserDto(User user) {
        this.userId = user.getUserId();

        this.userPwd = user.getUserPwd();

        this.userEmail = user.getEmail();

        this.userStatus = user.getStatus();
    }
}
