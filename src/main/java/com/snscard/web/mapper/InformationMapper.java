package com.snscard.web.mapper;

import com.snscard.web.pojo.User_Information;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InformationMapper {
    User_Information queryUserInformation(String name);
}
