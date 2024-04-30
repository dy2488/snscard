package com.snscard.web.realm;

import com.snscard.web.mapper.UserMapper;
import com.snscard.web.pojo.Users;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class UsersRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
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
