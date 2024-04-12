package com.snscard.web.controller;
import com.snscard.web.service.UserService;
import com.snscard.web.utils.ResultVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "user")
public class User_Login_Register {
    @Resource
    private UserService userService;


    @RequestMapping(value = "login")
    public ResultVO login(@RequestParam("id") String username,@RequestParam("password") String password) {
        return userService.login(username,password);

    }
    @RequestMapping(value = "addUser")
    public ResultVO add_user(@RequestParam("id") String username,@RequestParam("password") String password) {
        return userService.addUser(username,password);
    }
    @RequestMapping(value="deleteUser")
    public ResultVO delete_user(@RequestParam("id") String username) {
        return userService.deleteUser(username);
    }
    @RequestMapping(value="updateUser")
    public ResultVO updateUser(@RequestParam("id") String username,@RequestParam("password") String password) {
        return userService.updateUser(username,password);
    }
}
