package com.nhnacademy.gateway.controller.task;


import com.nhnacademy.gateway.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/task")
@Slf4j
public class TaskController {
    private final TaskService service;

    @PostMapping("/register/{projectNo}")
    public String registerTask(@PathVariable("projectNo") Integer projectNo,
                               @RequestParam("title") String title,
                               @RequestParam("content") String content,
                               Principal principal){

        service.registerTask(projectNo,title,principal.getName(),content);
        return "redirect:/project/info?projectNo=" + projectNo;
    }
}
