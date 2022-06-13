package com.nhnacademy.account.data.response;

import com.nhnacademy.account.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginResponse {
    public UserLoginResponse(User user) {
        this.id = user.getUserId();
        this.pw = user.getUserPw();
        this.email = user.getUserEmail();
        this.state = user.getUserState();
    }

    private String id;

    private String pw;

    private String email;

    private String state;
}
