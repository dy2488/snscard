package com.snscard.web.service;

import com.snscard.web.mapper.UserMapper;
import com.snscard.web.pojo.Users;
import com.snscard.web.utils.ResultVO;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;
    @Override
    public ResultVO login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Users users = userMapper.queryUsers(username);
        if(users == null ){
            return new ResultVO(1000,"사용자 없습니다",null);
        }else{
            if(users.getPassword().equals(password)){
                return new ResultVO(1001,"로그인 성공",users);
            }else{
                return new ResultVO(1002,"비밀번호가 틀렸습니다",null);
            }
        }
    }

    @Override
    public ResultVO addUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        Users users = userMapper.queryUsers(username);
        if(users == null){
            userMapper.addUser(username, password);
            return new ResultVO(1003,"사용자 추가 성공",null);
        }
        return new ResultVO(1004,"이미 존재하는 사용자입니다",null);
    }
}

