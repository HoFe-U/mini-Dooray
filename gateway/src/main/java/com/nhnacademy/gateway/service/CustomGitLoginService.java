package com.nhnacademy.gateway.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.gateway.data.response.UserResponse;
import com.nhnacademy.gateway.data.git.AuthToken;
import com.nhnacademy.gateway.data.git.GitCode;
import com.nhnacademy.gateway.data.git.GitProfile;
import com.nhnacademy.gateway.data.git.State;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service("customGitLoginService")
@RequiredArgsConstructor
public class CustomGitLoginService {

    private static final String CLIENT_ID ="7619e9831f585ede6baf";
    private static final String CLIENT_PWD ="57132a4c25ea849c42f7a776d37a0977e9614144";
    private static final String REDIRECT_URL = "http://localhost:8080/login/oauth2/code/github";
    private final UserService userService;
    private final ConcurrentHashMap<String, String> cookie = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, String> getCookie() {
        return cookie;
    }

    public UserResponse findUserByEmail(GitProfile gitProfile, HttpServletResponse response) throws IOException {
        if (Objects.isNull(userService.findByUserEmail(gitProfile.getEmail()))) {
            response.sendRedirect("auth/login");
        }

        return userService.findByUserEmail(gitProfile.getEmail());
    }

    public GitProfile getGithubProfile(AuthToken authToken) throws JsonProcessingException {
        RestTemplate requestTemplate = new RestTemplate();
        ResponseEntity<String> profileResponse = requestTemplate.exchange(
                "https://api.github.com/user",
                HttpMethod.GET,
                getProfileRequestEntity(authToken),
                String.class
        );

        log.warn("profile check : {}",profileResponse.getBody().toString());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(profileResponse.getBody(), GitProfile.class);
    }

    public State gitRequest() {
        String state = UUID.randomUUID().toString();

        UriComponents url = UriComponentsBuilder.fromHttpUrl("https://github.com/login/oauth/authorize")
                .queryParam("client_id", CLIENT_ID)
                .queryParam("redirect_uri", REDIRECT_URL)
                .queryParam("scope", "user")
                .queryParam("state", state)
                .build();
        cookie.put("state", state);

        return new State(state, url.toString());
    }

    public AuthToken getAuthToken(GitCode codeGit) throws JsonProcessingException {
        HttpEntity<MultiValueMap<String, String>> codeRequestHttpEntity = getCodeRequestHttpEntity(codeGit.getCode());
        RestTemplate tokenRequestTemplate = new RestTemplate();

        log.warn("codeGit check : {}" , codeGit.toString());
        ResponseEntity<String> response = tokenRequestTemplate.exchange(
                "https://github.com/login/oauth/access_token",
                HttpMethod.POST,
                codeRequestHttpEntity,
                String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.getBody(), AuthToken.class);
    }

    private HttpEntity<MultiValueMap<String, String>> getCodeRequestHttpEntity(String code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", CLIENT_ID);
        params.add("client_secret", CLIENT_PWD);
        params.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");

        return new HttpEntity<>(params, headers);
    }

    private HttpEntity<MultiValueMap<String, String>> getProfileRequestEntity(AuthToken authToken) {

        HttpHeaders infoRequestHeaders = new HttpHeaders();
        infoRequestHeaders.add("Authorization", "token " + authToken.getAccessToken());
        return new HttpEntity<>(infoRequestHeaders);
    }

    public void doGitLogin(UserResponse userInfo) {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_MEMBER");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        UserDetails userDetails = new User(userInfo.getId(), userInfo.getPw(), authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "USER_PASSWORD", authorities);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
    }
}
