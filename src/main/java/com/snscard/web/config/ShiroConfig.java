package com.snscard.web.config;

import com.snscard.web.realm.UsersRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

//    @Bean
//    public DefaultWebSessionManager sessionManager() {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        //去掉shiro登录时url里的jsessionid
//        sessionManager.setSessionIdUrlRewritingEnabled(false);
//        return sessionManager;
//
//    }
//    @Bean
//    public DefaultWebSecurityManager securityManager() {
//        DefaultWebSecurityManager manager= new DefaultWebSecurityManager();
//        manager.setRealm(usersRealm());
//        return manager;
//    }
//
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //권한 설치
        Map<String, String> filterChainDefinitionMap = new HashMap<>();
        filterChainDefinitionMap.put("/add", "authc");
        filterChainDefinitionMap.put("/update", "authc");
        filterChainDefinitionMap.put("/update_password", "authc");
        filterChainDefinitionMap.put("/login", "anon");



        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //등록페이지
        shiroFilterFactoryBean.setLoginUrl("/login");
        return shiroFilterFactoryBean;
    }


    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("usersRealm")UsersRealm usersRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(usersRealm);
        return manager;
    }

    @Bean
    public UsersRealm usersRealm() {
        return new UsersRealm();
    }
}
