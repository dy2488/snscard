package com.snscard.web.mapper;

import com.snscard.web.pojo.Users;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    Users queryUsers(String name);
    void addUser(String name,String password);
    void deleteUser(String name);
    void updateUser(String name,String password);
    String getSalt(String name);
    void setSalt(String name,String salt);


}
