package com.nhnacademy.gateway.config;

import com.nhnacademy.gateway.handler.LoginSuccessHandler;
import com.nhnacademy.gateway.service.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       return httpSecurity
                .authorizeRequests()
                .antMatchers("/", "/index", "/login", "/join").permitAll()
                .antMatchers("/project/*", "/logout", "/members/*", "/comment/*"
                , "/mile/*", "/tag/*", "/task/*").hasAnyAuthority("ROLE_MEMBER")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .usernameParameter("id")
                .passwordParameter("pw")
                .loginPage("/auth/login")
                .loginProcessingUrl("/login")
                .successHandler(loginSuccessHandler(null))
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .csrf()
                .disable()
                .headers()
                .defaultsDisabled()
                .frameOptions()
                .sameOrigin()
                .and()
                .authenticationProvider(authenticationProvider(null))
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler(RedisTemplate<String, String> redisTemplate) {
        return new LoginSuccessHandler(redisTemplate);
    }
}
