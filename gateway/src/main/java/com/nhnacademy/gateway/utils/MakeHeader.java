package com.nhnacademy.gateway.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

public class MakeHeader {
    public static HttpHeaders header(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
