package com.nhnacademy.task.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "milestones")
public class Milestone {
    @Id
    @Column(name = "milestone_no")
    private Integer milestoneNo;

    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;

    @OneToOne
    @JoinColumn(name = "task_no")
    private Task task;

    @Column(name = "milestone_content")
    private String content;

    @Column(name = "milestone_start")
    private LocalDateTime startTime;

    @Column(name = "milestone_end")
    private LocalDateTime endTime;


}
