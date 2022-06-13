package com.nhnacademy.task.entity;

import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_no")
    private Integer taskNo;

    @ManyToOne
    @JoinColumn(name = "task_member_no",referencedColumnName = "member_no")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;

    @Column(name = "task_content")
    private String content;

    @OneToOne(mappedBy = "task")
    private Milestone milestone;

    @Builder
    public Task(Integer taskNo, Member member, Project project, String content, Milestone milestone) {
        this.taskNo = taskNo;
        this.member = member;
        this.project = project;
        this.content = content;
        this.milestone = milestone;
    }
}
