package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.data.request.ProjectRegisterRequest;
import com.nhnacademy.gateway.data.response.ResponseProjectList;
import com.nhnacademy.gateway.data.response.ResponseProject;

import java.util.List;

public interface ProjectService {

    List<ResponseProjectList> getProjectList(String id);

    void registerProject(ProjectRegisterRequest projectRegisterRequest);

    void projectModifyState(Integer projectNo, String state);

    ResponseProject getProject(Integer projectNo);

    void projectMemberRegister(Integer projectNo, Integer memberNo);

    void projectMemberRemove(String projectNo, String memberNo);
}
