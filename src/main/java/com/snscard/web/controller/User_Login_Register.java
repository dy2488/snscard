package com.snscard.web.controller;

import com.snscard.web.mapper.UserMapper;
import com.snscard.web.pojo.Users;
import com.snscard.web.service.UserService;
import com.snscard.web.utils.ResultVO;
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
    private UserService userService;


    @RequestMapping(value = "login")
    public ResultVO login(@RequestParam("username") String username,@RequestParam("password") String password) {
        return userService.login(username,password);

    }
}
