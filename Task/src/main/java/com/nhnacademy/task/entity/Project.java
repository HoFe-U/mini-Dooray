package com.nhnacademy.task.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @Column(name = "project_no")
    private Integer projectNo;

    @ManyToOne
    @JoinColumn(name = "admin_no", referencedColumnName="member_no")
    private Member member;

    @Column(name = "project_status")
    private String status;

    @Column(name = "projecr_title")
    private String title;

    @Column(name = "date_of_registraion")
    private LocalDateTime registrationTime;

    @Column(name = "deadline_date")
    private LocalDateTime deadline;

}
