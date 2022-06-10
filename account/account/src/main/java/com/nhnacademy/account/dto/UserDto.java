package com.nhnacademy.account.dto;

import com.nhnacademy.account.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserDto {
    private String userName;
    private String userEmail;
    private String userStatus;
    public UserDto(User user) {
        this.userName = user.getUserName();

        this.userEmail = user.getEmail();

        this.userStatus = user.getStatus();
    }
}
