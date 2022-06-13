package com.nhnacademy.gateway.data.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class UserRegisterRequest {

    @NotBlank
    @Length(min = 1, max = 20)
    private String id;

    @NotBlank
    @Length(min = 1, max = 20)
    private String pw;

    @NotBlank
    @Email
    private String email;
}
