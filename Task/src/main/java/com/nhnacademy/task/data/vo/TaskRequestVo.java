package com.nhnacademy.task.data.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class TaskRequestVo {

    @NotNull
    private Integer projectNo;

    @NotNull
    @Size(min = 1, max = 20)
    private String id;

    @NotNull
    @Size(min = 1, max = 20)
    private String title;

    @NotNull
    @Size(min = 1, max = 50)
    private String content;
}
