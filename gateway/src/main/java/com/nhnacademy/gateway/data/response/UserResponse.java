package com.nhnacademy.gateway.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {

    private String id;

    private String pw;

    private String email;

    private String state;
}
