package com.nhnacademy.gateway.data.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberResponse {
    private Integer userNo;

    private String id;

    private String userPwd;

    private String name;

    private String email;

    private String status;
}
