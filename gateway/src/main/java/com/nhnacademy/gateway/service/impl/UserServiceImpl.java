package com.nhnacademy.gateway.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.gateway.data.request.UserRegisterRequest;
import com.nhnacademy.gateway.data.response.UserIdResponse;
import com.nhnacademy.gateway.data.response.UserResponse;
import com.nhnacademy.gateway.service.UserService;
import com.nhnacademy.gateway.utils.MakeHeader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private static final String ACCOUNT_URL = "http://localhost:8090/user";

    @Override
    public UserResponse getUser(String id) {
        ResponseEntity<UserResponse> response = restTemplate.exchange(
                ACCOUNT_URL+"/get/"+id,
                HttpMethod.GET,
                new HttpEntity<>(MakeHeader.makeHeader()),
                UserResponse.class);

        return response.getBody();
    }

    @Override
    public UserResponse findByUserEmail(String email) {
        log.error("check email : {}" , email);
        ResponseEntity<UserResponse> response = restTemplate.exchange(
                ACCOUNT_URL+"/check/email/?email="+email,
            HttpMethod.GET,
            new HttpEntity<>(MakeHeader.makeHeader()),
            UserResponse.class);

        return response.getBody();
    }

    @Override
    public void registerUser(UserRegisterRequest userRegisterRequest) {

        HttpHeaders httpHeaders = MakeHeader.makeHeader();
        String request;
        try {
            request = mapper.writeValueAsString(userRegisterRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(request, httpHeaders);
        restTemplate.exchange(
                ACCOUNT_URL+"/register",
                HttpMethod.POST,
                requestEntity,
                Void.class);
    }

    @Override
    public void modifyUserState(String user, String state) {
        restTemplate.exchange(
                ACCOUNT_URL+"/modify/" + user + "/" + state,
                HttpMethod.PUT,
                new HttpEntity<>(MakeHeader.makeHeader()),
                Void.class);
    }
}
