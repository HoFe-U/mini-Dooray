package com.nhnacademy.task.entity;

import javax.persistence.*;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @Column(name = "member_no")
    private Integer memberNo;

    @Column(name = "name")
    private String name;


}
