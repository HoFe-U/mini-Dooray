package com.nhnacademy.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{

        return httpSecurity.authorizeRequests()
                    .antMatchers("/login").hasAnyAuthority("ROLE_MEMBER")
                    .anyRequest().permitAll()
                    .and()
                    .oauth2Login()
                    .clientRegistrationRepository(clientRegistrationRepository())
                    .authorizedClientService(authorizedClientService())
                    .and()
                .formLogin()
                    .usernameParameter("id")
                .passwordParameter("pwd")
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").hasAnyAuthority("ROLE_MEMBER")
                .anyRequest().permitAll()
                .and()
                .oauth2Login()
                .clientRegistrationRepository(clientRegistrationRepository())
                .authorizedClientService(authorizedClientService())
                .and()
            .formLogin()
                .usernameParameter("id")
                .passwordParameter("pwd")
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(loginSuccessHandler(null))
                .and()
                .logout()
                .and()
//                .csrf()
//                .and()
                .sessionManagement()
                .sessionFixation()
                .none()
                .and()
                .headers()
                .defaultsDisabled()
                .frameOptions().sameOrigin()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error/403")
                .and();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider(null));
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

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(ClientRegistration.withRegistrationId("naver")
                .clientId("i1uKug9bdiBnP3FLed03")
                .clientSecret("4RkRczMtEY")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .scope("name", "email", "profile_image")
                .redirectUri("{baseUrl}/{action}/oauth2/code/{registrationId}")
                .authorizationUri("https://nid.naver.com/oauth2.0/authorize")
                .tokenUri("https://nid.naver.com/oauth2.0/token")
                .userInfoUri("https://openapi.naver.com/v1/nid/me")
                .userNameAttributeName("response")
                .build());
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }

}
