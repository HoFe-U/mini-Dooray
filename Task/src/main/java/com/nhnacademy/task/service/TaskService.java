package com.nhnacademy.task.service;

import com.nhnacademy.task.data.vo.TaskRequestVo;
import com.nhnacademy.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskService{
    void registerTask(TaskRequestVo taskRequestVo);

}
