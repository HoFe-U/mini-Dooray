package com.nhnacademy.task.data.response;

import com.nhnacademy.task.entity.Project;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectResponse {
    public ProjectResponse(Project project) {
        this.projectNo = project.getProjectNo();
        this.title = project.getTitle();
        this.status = project.getStatus();
        this.admin = project.getMember().getName();
    }

    private Integer projectNo;
    private String title;
    private String status;
    private String admin;}
