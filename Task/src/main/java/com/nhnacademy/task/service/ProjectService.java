package com.nhnacademy.task.service;

import com.nhnacademy.task.data.response.ProjectResponse;
import com.nhnacademy.task.data.response.ProjectView;
import com.nhnacademy.task.data.vo.ProjectMemberRequestVo;
import com.nhnacademy.task.data.vo.ProjectRequestVo;
import com.nhnacademy.task.entity.Project;

import java.util.List;

public interface ProjectService {
    List<ProjectView> getProjects(String userId);
    void registerProject(ProjectRequestVo requestVo);

    ProjectResponse getProject(Integer projectNo);

    void modifyState(Integer projectNo, String status);

    void registerProjectMember(ProjectMemberRequestVo projectMemberRequestVo);

    void deleteProjectMember(Integer projectNo, Integer memberNo);
}
