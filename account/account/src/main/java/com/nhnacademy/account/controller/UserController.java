package com.nhnacademy.account.controller;

import com.nhnacademy.account.data.response.UserIdResponse;
import com.nhnacademy.account.data.response.UserResponse;
import com.nhnacademy.account.data.response.UserLoginResponse;
import com.nhnacademy.account.exception.AlreadyUserException;
import com.nhnacademy.account.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @GetMapping
    public List<UserIdResponse> getStateOKUsers() {
        return service.getUserList();
    }

    @GetMapping("/get/{id}")
    public UserLoginResponse getUserById(@PathVariable("id") String id) {
        return service.findUserById(id);
    }

    @GetMapping("/check/email")
    public UserLoginResponse getUserByEmail(@RequestParam("email") String email) {
        return service.findUserByEmail(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void registerUser(@RequestBody @Valid UserResponse response,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new AlreadyUserException(bindingResult);
        }
        service.userRegister(response);
    }

    @PutMapping("/modify/{user}/{state}")
    public void modifyUserState(@PathVariable("user") String user,
                                @PathVariable("state") String state) {

        service.userStateModify(user, state);
    }
}
