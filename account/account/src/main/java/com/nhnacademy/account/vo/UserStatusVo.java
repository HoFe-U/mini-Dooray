package com.nhnacademy.account.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserStatusVo {

    private String userId;

    private String status;

    public UserStatusVo(String userId, String status) {
        this.userId = userId;
        this.status = status;
    }
}
