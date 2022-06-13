package com.nhnacademy.task.entity;

import com.nhnacademy.task.entity.pk.ProjectMembersPk;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table
@Entity(name = "projects_members")
@NoArgsConstructor
public class ProjectMembers {
    public ProjectMembers(ProjectMembersPk projectMembersPk, Member member, Project project) {
        this.projectMembersPk = projectMembersPk;
        this.member = member;
        this.project = project;
    }

    @EmbeddedId
    private ProjectMembersPk projectMembersPk;

    @MapsId("memberNo")
    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;

    @MapsId("projectNo")
    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;
}
