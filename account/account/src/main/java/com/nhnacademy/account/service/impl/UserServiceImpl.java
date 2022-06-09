package com.nhnacademy.account.service.impl;

import com.nhnacademy.account.dto.UserDto;
import com.nhnacademy.account.entity.User;
import com.nhnacademy.account.exception.NoUserException;
import com.nhnacademy.account.exception.UserIdOverlapException;
import com.nhnacademy.account.repository.UserRepository;
import com.nhnacademy.account.service.UserService;
import com.nhnacademy.account.vo.UserRequestVo;
import com.nhnacademy.account.vo.UserResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = repository.findAll();
        if(users.isEmpty()){
            throw new NoUserException();
        }
        return users.stream()
                .map(user -> new UserDto(user))
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Integer userNo) {
        User user = repository.findById(userNo).orElseThrow(NoUserException::new);
        return new UserDto(user);
    }

    @Override
    @Transactional
    @Modifying
    public UserResponseVo replaceUser(Integer userNo, UserRequestVo userRequestVo) {
        User user = repository.findById(userNo).orElseThrow(NoUserException::new);
        user.setUserId(userRequestVo.getId());
        user.setUserName(userRequestVo.getName());
        user.setUserPwd(userRequestVo.getPwd());
        user.setEmail(userRequestVo.getEmail());
        user.setStatus(userRequestVo.getStatus());
        repository.flush();

        return new UserResponseVo(user);
    }

    @Override
    public UserDto checkUserIdAndPwd(String userId, String userPwd) {
        User user = repository.findByUserIdAndUserPwd(userId, userPwd).orElseThrow(NoUserException::new);
        return new UserDto(user);
    }

    @Override
    @Transactional
    public UserResponseVo createUser(UserRequestVo userRequestVo) {
        if(repository.existsByUserId(userRequestVo.getId())){
            throw new UserIdOverlapException();
        }
        User user = new User(userRequestVo);
        repository.save(user);
        return new UserResponseVo(user);
    }

    @Override
    public void removeUser(Integer userNo) {
        repository.deleteById(userNo);
    }
}
