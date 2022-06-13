package com.nhnacademy.gateway.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRegisterRequest {

    private String id;

    private String title;

    private String content;
}
