package com.nhnacademy.gateway.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.gateway.data.request.ProjectMemberRequest;
import com.nhnacademy.gateway.data.request.ProjectRegisterRequest;
import com.nhnacademy.gateway.data.response.ResponseProjectList;
import com.nhnacademy.gateway.data.response.ResponseProject;
import com.nhnacademy.gateway.service.ProjectService;
import java.util.List;

import com.nhnacademy.gateway.utils.MakeHeader;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private final String PROJECT_URL = "http://localhost:9090/project";
    @Override
    public List<ResponseProjectList> getProjectList(String id) {
        ResponseEntity<List<ResponseProjectList>>
            response = restTemplate.exchange(PROJECT_URL+"/view/" + id,
            HttpMethod.GET,
            new HttpEntity<>(MakeHeader.makeHeader()),
            new ParameterizedTypeReference<>() {
            });

        return response.getBody();
    }

    @Override
    public void registerProject(ProjectRegisterRequest projectRegisterRequest) {
        String mapper;
        try {
            mapper = this.mapper.writeValueAsString(projectRegisterRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(mapper, MakeHeader.makeHeader());
        restTemplate.exchange(PROJECT_URL +"/register",
            HttpMethod.POST,
            requestEntity,
            Void.class);
    }

    @Override
    public ResponseProject getProject(Integer projectNo) {
        ResponseEntity<ResponseProject> exchange = restTemplate.exchange(
                PROJECT_URL + "/get/" + "?projectNo=" + projectNo,
                HttpMethod.GET,
                new HttpEntity<>(MakeHeader.makeHeader()),
                ResponseProject.class
        );
        return exchange.getBody();
    }

    @Override
    public void projectMemberRegister(Integer projectNo, Integer memberNo) {
        ProjectMemberRequest projectMemberRequest = new ProjectMemberRequest(projectNo, memberNo);
        String mapper;
        try {
            mapper = this.mapper.writeValueAsString(projectMemberRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        HttpEntity<String> entity = new HttpEntity<>(mapper, MakeHeader.makeHeader());
        restTemplate.exchange(
                PROJECT_URL+"/register/member",
                HttpMethod.POST,
                entity,
                Void.class);
    }

    @Override
    public void projectMemberRemove(String projectNo, String memberNo) {
        restTemplate.exchange(
                PROJECT_URL+"/delete/member/"+projectNo +"?memberNo="+memberNo,
                HttpMethod.DELETE,
                new HttpEntity<>(MakeHeader.makeHeader()),
                Void.class
        );
    }
    @Override
    public void projectModifyState(Integer projectNo, String status) {
        restTemplate.exchange(
                "http://localhost:9090/project/status/" + projectNo + "?status=" + status,
                HttpMethod.PUT,
                new HttpEntity<>(MakeHeader.makeHeader()),
                Void.class);
    }

}
