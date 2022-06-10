package com.nhnacademy.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.account.repository.UserRepository;
import com.nhnacademy.account.service.UserService;
import com.nhnacademy.account.vo.UserRequestVo;
import com.nhnacademy.account.vo.UserStatusVo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;


    @Test
    void getUser() throws Exception {
        mvc.perform(get("/account/checkUser")
                        .param("id", "a")
                        .param("pwd", "a")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void createUser() throws Exception {
        mvc.perform(post("/account/create")
                        .content(asJsonString(new UserRequestVo("a", "a", "a", "a@naver.com", "good")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void modifyUser() throws Exception {
        mvc.perform(put("/account/modify")
                        .content(asJsonString(new UserRequestVo("a", "a", "a", "a@naver.com", "good")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteUser() throws Exception {
        mvc.perform(delete("/account/delete/{id}", "id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void modifyStatus() throws Exception {
        mvc.perform(put("/account/modify/status")
                        .content(asJsonString(new UserStatusVo("a", "bad")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void findEmail() throws Exception{
        mvc.perform(get("/account/email")
                .param("email","email")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}