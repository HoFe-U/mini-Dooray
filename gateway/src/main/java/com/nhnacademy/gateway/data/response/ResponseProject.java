package com.nhnacademy.gateway.data.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseProject {
    private Integer projectNo;
    private String title;
    private String status;
    private String admin;
}
