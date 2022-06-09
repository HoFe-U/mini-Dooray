package com.nhnacademy.task.entity;

import javax.persistence.*;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @Column(name = "tag_no")
    private Integer tagNo;

    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;

    @Column(name = "tag_content")
    private String tagContent;

}
