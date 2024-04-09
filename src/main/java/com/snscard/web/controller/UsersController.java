package com.snscard.web.controller;

import com.snscard.web.mapper.UserMapper;
import com.snscard.web.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/queryUserList")
    public List<Users> queryUserList()
    {
        return userMapper.queryUserList();
    }
}
