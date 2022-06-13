package com.nhnacademy.gateway.service.impl;

import com.nhnacademy.gateway.data.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<UserResponse> response = restTemplate.exchange(
                "http://localhost:8090/user/get/" + username,
                HttpMethod.GET,
                requestEntity,
                UserResponse.class);

        if(Objects.requireNonNull(response.getBody()).getId().equals(username)) {

            UserResponse userInfo = response.getBody();
            return new User(Objects.requireNonNull(userInfo).getId(), userInfo.getPw(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_MEMBER")));
        }

        throw new UsernameNotFoundException(username + "not found");
    }
}
