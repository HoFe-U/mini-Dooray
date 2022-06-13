package com.nhnacademy.task.data.response;

import com.nhnacademy.task.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectView {
    private Integer projectNo;
    private String admin;
    private String status;
    private String title;

    public ProjectView(Project project) {
        this.projectNo = project.getProjectNo();
        this.admin = project.getMember().getName();
        this.status = project.getStatus();
        this.title = project.getTitle();
    }
}
