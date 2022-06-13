package com.nhnacademy.task.service.impl;

import com.nhnacademy.task.data.response.ProjectResponse;
import com.nhnacademy.task.data.response.ProjectView;
import com.nhnacademy.task.data.vo.ProjectMemberRequestVo;
import com.nhnacademy.task.data.vo.ProjectRequestVo;
import com.nhnacademy.task.entity.Member;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.ProjectMembers;
import com.nhnacademy.task.entity.pk.ProjectMembersPk;
import com.nhnacademy.task.exception.NoMemberException;
import com.nhnacademy.task.exception.NoProjectException;
import com.nhnacademy.task.repository.MemberRepository;
import com.nhnacademy.task.repository.ProjectMemberRepository;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final ProjectMemberRepository projectMemberRepository;
    @Override
    @Transactional
    public List<ProjectView> getProjects(String userId) {
        if(!memberRepository.existsByName(userId) && Objects.nonNull(userId)){
            memberRepository.save(new Member(userId));
        }
        Member member = memberRepository.findByName(userId).orElseThrow(NoMemberException::new);
        List<Project> projects = projectRepository.getProjectByMember(member);

        return projects.stream()
                .map(ProjectView::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void registerProject(ProjectRequestVo requestVo) {
        Member member = memberRepository.findByName(requestVo.getId()).orElseThrow(NoMemberException::new);
        Project project = Project.builder()
                .projectNo(null)
                .member(member)
                .registrationTime(LocalDateTime.now())
                .title(requestVo.getTitle())
                .status("ongoing")
                .content(requestVo.getContent())
                .build();

        projectRepository.save(project);
    }

    @Override
    public ProjectResponse getProject(Integer projectNo) {
        Project project = projectRepository.findById(projectNo).orElseThrow(NoProjectException::new);
        return new ProjectResponse(project);
    }

    @Override
    @Transactional
    @Modifying
    public void modifyState(Integer projectNo, String status) {
        Project project = projectRepository.findById(projectNo).orElseThrow(NoProjectException::new);
        project.setStatus(status);


        projectRepository.flush();
    }

    @Transactional
    @Override
    public void registerProjectMember(ProjectMemberRequestVo projectMemberRequestVo) {
        Project project = projectRepository.findById(projectMemberRequestVo.getProjectNo()).orElseThrow(NoProjectException::new);
        Member member = memberRepository.findById(projectMemberRequestVo.getMemberNo()).orElseThrow(NoMemberException::new);

        ProjectMembersPk pk = ProjectMembersPk.builder().
                projectNo(project.getProjectNo())
                .memberNo(member.getMemberNo()).build();

        ProjectMembers projectMembers = new ProjectMembers(pk, member, project);

        projectMemberRepository.save(projectMembers);
    }

    @Transactional
    @Override
    public void deleteProjectMember(Integer projectNo, Integer memberNo) {
        Project project = projectRepository.findById(projectNo).orElseThrow(NoProjectException::new);
        Member member = memberRepository.findById(memberNo).orElseThrow(NoMemberException::new);

        ProjectMembersPk pk = ProjectMembersPk.builder().
                projectNo(project.getProjectNo())
                .memberNo(member.getMemberNo()).build();

        projectMemberRepository.deleteById(pk);
    }
}
