package com.nhnacademy.account.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestVo {
    @Builder
    public UserRequestVo(String id, String pwd, String name, String email, String status) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.email = email;
        this.status = status;
    }
    private String id;

    private String pwd;

    private String name;

    private String email;

    private String status;
}
