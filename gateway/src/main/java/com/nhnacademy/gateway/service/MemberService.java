package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.data.request.UserRegisterRequest;
import com.nhnacademy.gateway.data.response.ResponseMember;
import com.nhnacademy.gateway.data.response.ResponseProject;

import java.util.List;

public interface MemberService {
    void registerMember(Integer projectNo, String id);

    void deleteMember(Integer projectNo, String id);

    List<ResponseMember> getMembers(Integer projectNo);

    List<ResponseMember> getProjectMembers(Integer projectNo);

    void signUp(UserRegisterRequest request);
}
