package com.snscard.web.controller;
import com.snscard.web.service.UserService;
import com.snscard.web.utils.ResultVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "user")
public class User_Login_Register {
    @Resource
    private UserService userService;


    @RequestMapping(value = "login")
    public ResultVO login(@RequestParam("id") String username, @RequestParam("password") String password) {
        return userService.login(username,password);

    }
    @RequestMapping(value = "register")
    public ResultVO add_user(@RequestParam("id") String username, @RequestParam("password") String password) {
        return userService.addUser(username,password);
    }
    public ResultVO delete_user(HttpSession session) {
        return userService.deleteUser(session);
    }
    @RequestMapping(value="update_password")
    public ResultVO updateUser(@RequestParam("password")String password) {
        return userService.updateUser(password);
    }
    @RequestMapping(value = "logout")
    public ResultVO Logout() {
        return userService.Logout();
    }

}
