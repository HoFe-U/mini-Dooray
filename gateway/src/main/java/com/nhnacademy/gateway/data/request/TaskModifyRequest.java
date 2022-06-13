package com.nhnacademy.gateway.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskModifyRequest {

    private Integer taskNo;

    private String title;

    private String content;
}
