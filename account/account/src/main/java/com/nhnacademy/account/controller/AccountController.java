package com.nhnacademy.account.controller;

import com.nhnacademy.account.dto.UserDto;
import com.nhnacademy.account.service.UserService;
import com.nhnacademy.account.vo.UserRequestVo;
import com.nhnacademy.account.vo.UserResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    //port : 8090
    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/checkUser")
    public UserDto getUser(@RequestParam("id") String id,
                           @RequestParam("pwd")String pwd){

       return userService.checkUserIdAndPwd(id,pwd);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public UserResponseVo createUser(@RequestBody UserRequestVo userRequestVo){
       return userService.createUser(userRequestVo);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping ("/modify/{id}")
    UserResponseVo modifyUser(@PathVariable("id") Integer id,
                              @RequestBody UserRequestVo userRequestVo){
        return userService.replaceUser(id, userRequestVo);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        userService.removeUser(id);
    }

}

