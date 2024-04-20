package com.snscard.web.service;

import com.snscard.web.mapper.UserMapper;
import com.snscard.web.pojo.Users;
import com.snscard.web.utils.ResultVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public ResultVO login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
            subject.getSession().setAttribute("username", username);
        } catch (UnknownAccountException e) {
            return new ResultVO(1000, "사용자 정보 없습니다");
        } catch (IncorrectCredentialsException e) {
            return new ResultVO(1002, "비밀번호가 틀렸습니다");
        }
        return new ResultVO(1001, "로그인 성공");
    }

    @Override
    public ResultVO addUser(String username, String password) {
        Users users = userMapper.queryUsers(username);
        if (users == null) {
            userMapper.addUser(username, password);
            return new ResultVO(1003, "사용자 추가 성공");
        }
        return new ResultVO(1004, "이미 존재하는 사용자입니다");
    }

    @Override
    public ResultVO deleteUser(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) session.getAttribute("username");
        userMapper.deleteUser(username);
        String msg=username+"고객님 정보 삭제되었습니다";
        return new ResultVO(1007,msg);
    }

    @Override
    public ResultVO updateUser(String password) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String)subject.getSession().getAttribute("username");
        userMapper.updateUser(username, password);
        subject.logout();
        return new ResultVO(1006, "사용자정보 수정되었습니다");
    }

    @Override
    public ResultVO Logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ResultVO(2000, "로그아웃 성공");
    }
}

