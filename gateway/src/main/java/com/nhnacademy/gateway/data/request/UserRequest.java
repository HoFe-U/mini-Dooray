package com.nhnacademy.gateway.data.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRequest {
    private String id;

    private String pwd;

    private String name;

    private String email;

    private String status;

    @Builder
    public UserRequest(String id, String pwd, String name, String email, String status) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.email = email;
        this.status = status;
    }
}
