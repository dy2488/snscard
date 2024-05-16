package com.snscard.web.mapper;

import com.snscard.web.pojo.UserImage;
import com.snscard.web.pojo.User_Information;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InformationMapper {
    User_Information queryUserInformation(String name);
    void insertUserInformation(User_Information user_information);
    void updateUserInformation(User_Information user_information);
    void insertUserPath(String id,String name, String image_all_name);
    void insertUserAnswer(String id,String name,String a1,String a2,String a3,String a4,String a5);
    List<String> queryAllUserImage(UserImage user_image);
    void insertUserCropPath(String id,String name,String image_all_name);
    List<String> queryAllUserCropperImage(UserImage user_image);
}
