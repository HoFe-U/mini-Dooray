package com.nhnacademy.task.service.impl;

import com.nhnacademy.task.data.vo.TaskRequestVo;
import com.nhnacademy.task.entity.Member;
import com.nhnacademy.task.entity.Project;
import com.nhnacademy.task.entity.Task;
import com.nhnacademy.task.exception.NoMemberException;
import com.nhnacademy.task.exception.NoProjectException;
import com.nhnacademy.task.repository.MemberRepository;
import com.nhnacademy.task.repository.ProjectRepository;
import com.nhnacademy.task.repository.TaskRepository;
import com.nhnacademy.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    @Override
    public void registerTask(TaskRequestVo taskRequestVo) {
        Member member = memberRepository.findByName(taskRequestVo.getId()).orElseThrow(NoMemberException::new);
        Project project = projectRepository.findById(taskRequestVo.getProjectNo()).orElseThrow(NoProjectException::new);

        Task build = Task.builder()
                .member(member)
                .project(project)
                .content(taskRequestVo.getContent())
                .build();

        taskRepository.save(build);
    }
}
