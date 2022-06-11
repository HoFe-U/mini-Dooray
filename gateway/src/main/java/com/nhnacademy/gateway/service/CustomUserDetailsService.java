package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.data.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        ResponseEntity<UserRequest> userVo = restTemplate.exchange("http://localhost:8090/account/checkUser/" + username,
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                UserRequest.class
        );

        UserRequest body = userVo.getBody();

        return new User(body.getId(),body.getPwd(),
            Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER")));
    }
}
