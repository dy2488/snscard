package com.snscard.web.mapper;

import com.snscard.web.pojo.Users;
import com.snscard.web.pojo.Users_Path;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper {
    Users queryUsers(String name);
    void addUser(String name,String password);
    void deleteUser(String name);
    void updateUser(String name,String password);
    String getSalt(String name);
    void setSalt(String name,String salt);
    Users_Path queryUsersPath(String name);


}
