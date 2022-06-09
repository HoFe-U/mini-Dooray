package com.nhnacademy.task.entity;

import com.nhnacademy.task.entity.pk.ManagementPk;

import javax.persistence.*;

@Entity
@Table(name = "tag_task_management")
public class TagTaskManagement {
    @EmbeddedId
    private ManagementPk pk;

    @MapsId("tagNo")
    @ManyToOne
    @JoinColumn(name = "tag_no")
    private Tag tag;

    @MapsId("taskNo")
    @ManyToOne
    @JoinColumn(name = "taskNo")
    private Task task;
}
