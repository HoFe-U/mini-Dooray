package com.nhnacademy.account.controller;

import com.nhnacademy.account.dto.UserDto;
import com.nhnacademy.account.service.UserService;
import com.nhnacademy.account.vo.UserRequestVo;
import com.nhnacademy.account.vo.UserResponseVo;
import com.nhnacademy.account.vo.UserStatusVo;
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
    @GetMapping("/checkUser/{id}")
    public UserDto getUserById(@PathVariable("id")String id){
        return userService.getUser(id);
    }
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
    @PutMapping ("/modify")
    public UserResponseVo modifyUser(@RequestBody UserRequestVo userRequestVo){
        return userService.replaceUser(userRequestVo.getId(), userRequestVo);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") String userId){
        userService.removeUser(userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/modify/status")
    UserResponseVo modifyStatus(@RequestBody UserStatusVo userStatusVo){
        return userService.replaceUser(userStatusVo);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/email")
    public UserResponseVo findUserEmail(@RequestParam("email") String email){
        return userService.findEmail(email);
    }

}

