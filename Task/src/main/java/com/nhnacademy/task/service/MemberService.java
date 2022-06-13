package com.nhnacademy.task.service;

import com.nhnacademy.task.data.response.ResponseMember;

import java.util.List;

public interface MemberService {
    List<ResponseMember> getMembersNotInProject(Integer projectNo);

    List<ResponseMember> getMemberInProject(Integer projectNo);

    void registerMember(String id);
}
