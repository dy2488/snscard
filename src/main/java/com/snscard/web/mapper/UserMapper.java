package com.snscard.web.mapper;

import com.snscard.web.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper {
    Users queryUsers(String name);
    void addUser(String name,String password);
    void deleteUser(String name);
    void updateUser(String name,String password);


}
