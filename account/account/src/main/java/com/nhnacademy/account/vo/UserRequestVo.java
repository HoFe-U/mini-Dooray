package com.nhnacademy.account.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestVo {
    private String id;

    private String pwd;

    private String name;

    private String email;

    private String status;
}
