package com.nhnacademy.account.vo;

import com.nhnacademy.account.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserResponseVo {
    private Integer userNo;

    private String id;

    private String userPwd;

    private String name;

    private String email;

    private String status;

    public UserResponseVo(User user) {
        this.userNo = user.getUserNo();
        this.id = user.getUserId();
        this.userPwd = user.getUserPwd();
        this.name = user.getUserName();
        this.email = user.getEmail();
        this.status = user.getStatus();
    }
}
