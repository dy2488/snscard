package com.snscard.web.realm;

import com.snscard.web.mapper.UserMapper;
import com.snscard.web.pojo.Users;
import jakarta.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class UsersRealm extends AuthorizingRealm {
    @Resource
    private UserMapper userMapper;
    //授权
    //권한 부여
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    //认证
    //인증
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        Users users = userMapper.queryUsers(token.getUsername());
        if(users != null){
            return new SimpleAuthenticationInfo(users, users.getPassword(), this.getName());
        }

        return null;
    }
}
