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
            if(users.getPassWord().equals(password)){
                return new ResultVO(1001,"로그인 성공",users);
            }else{
                return new ResultVO(1002,"비밀번호가 틀렸습니다",null);
            }
        }
    }
}

