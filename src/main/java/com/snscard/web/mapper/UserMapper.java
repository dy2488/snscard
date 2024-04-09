package com.snscard.web.mapper;

import com.snscard.web.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<Users> queryUserList();
    Users queryById(int id);

    int addUser(Users user);

    int updateUser(Users user);

    int deleteUser(int id);

}
