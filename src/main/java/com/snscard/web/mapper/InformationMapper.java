package com.snscard.web.mapper;

import com.snscard.web.pojo.User_Information;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InformationMapper {
    User_Information queryUserInformation(String name);
    void insertUserInformation(User_Information user_information);
    void insertUserPath(String name, User_Information user_information);
}
