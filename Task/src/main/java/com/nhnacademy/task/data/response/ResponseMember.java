package com.nhnacademy.task.data.response;

import com.nhnacademy.task.entity.Member;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ResponseMember {
    @NotNull
    private Integer memberNo;
    @NotNull
    private String id;

    public ResponseMember(Member member) {
        this.memberNo = member.getMemberNo();
        this.id = member.getName();
    }
}
