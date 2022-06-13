package com.nhnacademy.gateway.data.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseTask {
    private Integer taskNo;
    private String content;
    private String title;
}
