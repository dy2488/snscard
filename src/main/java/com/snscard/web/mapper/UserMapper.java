package com.snscard.web.mapper;

import com.snscard.web.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    Users queryUsers(String name);

}
