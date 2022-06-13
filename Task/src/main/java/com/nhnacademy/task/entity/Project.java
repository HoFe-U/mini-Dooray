package com.nhnacademy.task.entity;

import com.nhnacademy.task.data.vo.ProjectRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_no")
    private Integer projectNo;

    @ManyToOne
    @JoinColumn(name = "admin_no", referencedColumnName="member_no")
    private Member member;

    @Column(name = "project_status")
    private String status;

    @Column(name = "projecr_title")
    private String title;

    @Column(name = "project_content")
    private String content;
    @Builder
    public Project(Integer projectNo, Member member, String status, String title, String content, LocalDateTime registrationTime) {
        this.projectNo = projectNo;
        this.member = member;
        this.status = status;
        this.title = title;
        this.content = content;
        this.registrationTime = registrationTime;
    }




    @Column(name = "date_of_registraion")
    private LocalDateTime registrationTime;

}
