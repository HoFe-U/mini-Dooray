package com.nhnacademy.gateway.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RestTempleUtils {
    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(3000);
        requestFactory.setConnectionRequestTimeout(3000);
        requestFactory.setReadTimeout(3000);

        return new RestTemplate(requestFactory);
    }
}
