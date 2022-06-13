package com.nhnacademy.gateway.service.impl;

import com.nhnacademy.gateway.data.request.UserRegisterRequest;
import com.nhnacademy.gateway.data.response.ResponseMember;
import com.nhnacademy.gateway.data.response.ResponseProject;
import com.nhnacademy.gateway.service.MemberService;
import com.nhnacademy.gateway.utils.MakeHeader;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final RestTemplate template;

    private final String MEMBER_URL = "http://localhost:9090/members";
    @Override
    public void registerMember(Integer projectNo, String id) {

        template.exchange(
                MEMBER_URL+"/register?projectNo=" + projectNo + "&id=" + id,
                HttpMethod.POST,
                new HttpEntity<>(MakeHeader.makeHeader()),
                Void.class);
    }

    @Override
    public void deleteMember(Integer projectNo, String id) {

        template.exchange(
                MEMBER_URL+"/delete?projectNo=" + projectNo + "&id=" + id,
                HttpMethod.DELETE,
                new HttpEntity<>(MakeHeader.makeHeader()),
                Void.class);
    }

    @Override
    public List<ResponseMember> getMembers(Integer projectNo) {

        ResponseEntity<List<ResponseMember>> response =
        template.exchange(MEMBER_URL + "/get/notIn?projectNo=" + projectNo,
                HttpMethod.GET,
                new HttpEntity<>(MakeHeader.makeHeader()),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }

    @Override
    public List<ResponseMember> getProjectMembers(Integer projectNo) {

        ResponseEntity<List<ResponseMember>> response =
        template.exchange(MEMBER_URL + "/get/in?projectNo=" + projectNo,
                HttpMethod.GET,
                new HttpEntity<>(MakeHeader.makeHeader()),
                new ParameterizedTypeReference<>() {
                });

        return response.getBody();
    }

    @Override
    public void signUp(UserRegisterRequest request) {
        String id = request.getId();
        template.exchange(MEMBER_URL+"/register?id="+id,
                HttpMethod.POST,
                new HttpEntity<>(MakeHeader.makeHeader()),
                Void.class);
    }
}
