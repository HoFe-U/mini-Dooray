package com.nhnacademy.gateway.data.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectMemberRequest {
    public ProjectMemberRequest(Integer projectNo, Integer memberNo) {
        this.projectNo = projectNo;
        this.memberNo = memberNo;
    }

    private Integer projectNo;

    private Integer memberNo;
}
