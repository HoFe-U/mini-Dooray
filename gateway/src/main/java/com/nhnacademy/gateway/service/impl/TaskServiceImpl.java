package com.nhnacademy.gateway.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.gateway.data.request.TaskModifyRequest;
import com.nhnacademy.gateway.data.request.TaskRegisterRequest;
import com.nhnacademy.gateway.service.TaskService;
import com.nhnacademy.gateway.utils.MakeHeader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final RestTemplate template;
    private final ObjectMapper mapper;
    private final String TASK_URL = "http://localhost:9090/task";

    @Override
    public void registerTask(Integer projectNo, String id, String title, String content) {

        TaskRegisterRequest taskRegisterRequest = new TaskRegisterRequest(projectNo, id, title, content);

        String request = "";

        try {
            request = mapper.writeValueAsString(taskRegisterRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, MakeHeader.makeHeader());
        template.exchange(TASK_URL+"/register",
                HttpMethod.POST,
                requestEntity,
                Void.class);
    }

    @Override
    public void modifyTask(Integer taskNo, String title, String content) {

        TaskModifyRequest taskModifyRequest = new TaskModifyRequest(taskNo, title, content);
        String request = "";

        try {
            request = mapper.writeValueAsString(taskModifyRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(request, MakeHeader.makeHeader());
        template.exchange(TASK_URL+"/modify",
                HttpMethod.PUT,
                requestEntity,
                Void.class);
    }

    @Override
    public void deleteTask(Integer taskNo) {
        template.exchange(TASK_URL+"/delete/" + taskNo,
                HttpMethod.DELETE,
                new HttpEntity<>(MakeHeader.makeHeader()),
                Void.class);
    }
}
