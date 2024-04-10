package com.snscard.web.controller;

import com.snscard.web.mapper.UserMapper;
import com.snscard.web.pojo.Users;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "user")
public class User_Login_Register {
    @Resource
    private UserMapper userMapper;
    @RequestMapping(value = "login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Users users=new Users();
        if(StringUtils.hasText(username) && StringUtils.hasText(password)) {
            users=userMapper.queryUsers(username, password);
            if(users!=null) {
                return "success";
            }

        }
        return "login_fail";


    }

}
