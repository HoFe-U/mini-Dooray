package com.nhnacademy.task.data.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@NoArgsConstructor
public class ProjectRequestVo {

    @NotNull
    @Size(min = 1,max = 20)
    private String id;
    @NotNull
    @Size(min=1,max = 50)
    private String title;
    @Size(min = 1,max = 100)
    private String content;

}
