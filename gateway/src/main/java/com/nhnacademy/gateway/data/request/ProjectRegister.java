package com.nhnacademy.gateway.data.request;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectRegister {

    @NotNull
    @Size(min=1, max=20)
    private String title;

    @NotNull
    @Size(min=1, max=100)
    private String content;
}
