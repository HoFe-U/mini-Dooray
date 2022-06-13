package com.nhnacademy.gateway.data.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProjectList {

    private Integer projectNo;

    private String admin;

    private String title;

    private String status;
}
