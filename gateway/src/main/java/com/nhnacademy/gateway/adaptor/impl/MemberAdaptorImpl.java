package com.nhnacademy.gateway.adaptor.impl;

import com.nhnacademy.gateway.adaptor.MemberAdaptor;
import com.nhnacademy.gateway.data.request.UserRequest;
import com.nhnacademy.gateway.data.response.MemberResponse;
import com.nhnacademy.gateway.utils.JsonToString;
import com.nhnacademy.gateway.utils.MakeHeader;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberAdaptorImpl implements MemberAdaptor {
    private final RestTemplate restTemplate;
    private final PasswordEncoder encoder;
    @Override
    public void joinMember(String id,String name, String pwd, String email) {
        UserRequest request = UserRequest.builder()
                .id(id)
                .pwd(encoder.encode(pwd))
                .email(email)
                .name(name)
                .status("good")
                .build();

        restTemplate.exchange("http://localhost:8090/account/create"
                , HttpMethod.POST
                , new HttpEntity<>(JsonToString.asJsonString(request), MakeHeader.header())
                , new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public MemberResponse findMemberId(String id) {
        return null;
    }

    @Override
    public MemberResponse findMemberEmail(String email) {
        return null;
    }

    @Override
    public void fixMemberInfo(String id, String pwd, String name, String email, String status) {

    }

    @Override
    public void fixMemberStatus(String status) {

    }
}
