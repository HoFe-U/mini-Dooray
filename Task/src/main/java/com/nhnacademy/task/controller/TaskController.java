package com.nhnacademy.task.controller;


import com.nhnacademy.task.data.vo.TaskRequestVo;
import com.nhnacademy.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/register")
    public void createTask(@RequestBody TaskRequestVo taskRequestVo){
        taskService.registerTask(taskRequestVo);
    }
}
