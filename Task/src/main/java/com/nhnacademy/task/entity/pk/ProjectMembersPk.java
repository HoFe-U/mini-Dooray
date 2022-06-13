package com.nhnacademy.task.entity.pk;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Setter
public class ProjectMembersPk implements Serializable {
    Integer memberNo;

    Integer projectNo;
    @Builder
    public ProjectMembersPk(Integer memberNo, Integer projectNo) {
        this.memberNo = memberNo;
        this.projectNo = projectNo;
    }
}
