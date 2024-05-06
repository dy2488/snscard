package com.snscard.web.service;

import com.snscard.web.mapper.UserMapper;
import com.snscard.web.pojo.Users;
import com.snscard.web.pojo.Users_Path;
import com.snscard.web.utils.ResultVO;
import com.snscard.web.utils.Result_Path;
import jakarta.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ResultVO login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        String salt = userMapper.getSalt(username);
        if(salt==null)
        {
            return new ResultVO(1000,"사용자 정보 없습니다");
        }
        SimpleHash pd_hash= new SimpleHash("SHA-256", password ,salt, 1024);
        UsernamePasswordToken token = new UsernamePasswordToken(username, pd_hash.toHex());
        try {
            subject.login(token);
//        } catch (UnknownAccountException e) {
//            return new ResultVO(1000, "사용자 정보 없습니다");
        } catch (IncorrectCredentialsException e) {
            return new ResultVO(1002, "비밀번호가 틀렸습니다");
        }
        subject.getSession().setAttribute("username", username);
        Users users = userMapper.queryUsers(username);
        return new ResultVO(1001, "로그인 성공",users.getName());
    }

    @Override
    public ResultVO addUser(String username, String password) {
        Users users = userMapper.queryUsers(username);
        if (users == null) {
            String uuid= UUID.randomUUID().toString();
            String salt=uuid+username;
            SimpleHash password_salt=new SimpleHash("SHA-256",salt,uuid,10);
            SimpleHash simpleHash=new SimpleHash("SHA-256",password,password_salt.toHex(),1024);
            userMapper.addUser(username,simpleHash.toHex());
            userMapper.setSalt(username,password_salt.toHex());
            return new ResultVO(1003, "사용자 추가 성공");
        }
        return new ResultVO(1004, "이미 존재하는 사용자입니다");
    }

    @Override
    public ResultVO deleteUser(HttpSession session) {
//        Subject subject = SecurityUtils.getSubject();
        String username = (String) session.getAttribute("username");
        userMapper.deleteUser(username);
        String msg=username+"고객님 정보 삭제되었습니다";
        return new ResultVO(1007,msg);
    }

    @Override
    public ResultVO updateUser(String password) {
        Subject subject = SecurityUtils.getSubject();
        String username=(String) subject.getSession().getAttribute("username");
        String salt = userMapper.getSalt(username);
        SimpleHash simpleHash = new SimpleHash("SHA-256", password, salt, 1024);
        userMapper.updateUser(username, simpleHash.toHex());
        subject.logout();
        return new ResultVO(1006, "비밀번호 수정되었습니다");
    }

    @Override
    public ResultVO Logout() {
        Subject subject = SecurityUtils.getSubject();
        Object username = subject.getSession().getAttribute("username");
        if(username==null){
            return new ResultVO(2001,"로그하지 않는 상태입니다.");
        }
        subject.logout();
        return new ResultVO(2000, "로그아웃 성공");
    }


}

