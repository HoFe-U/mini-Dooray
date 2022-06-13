package com.nhnacademy.task.entity;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "comment_no")
    private Integer commentNo;

    @ManyToOne
    @JoinColumn(name = "task_no")
    private Task task;

    @Column(name = "comment_content")
    private String content;
}
