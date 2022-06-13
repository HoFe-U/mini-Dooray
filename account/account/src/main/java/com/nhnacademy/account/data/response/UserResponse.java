package com.nhnacademy.account.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UserResponse {

    @NotBlank
    @Size(min = 1,max = 20)
    private String id;

    @NotBlank
    @Size(min = 1,max = 100)
    private String pw;

    @NotBlank
    @Size(min =1,max = 100)
    private String email;
}
