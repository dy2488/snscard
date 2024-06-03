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
    void insertUserPath(String uuid,String name, String image_all_name);
    void insertUserAnswer(String uuid,String name,String a1,String a2,String a3,String a4,String a5);
    List<String> queryAllUserImage(UserImage user_image);
    void insertUserCropPath(String uuid,String name,String image_all_name);
    List<String> queryAllUserCropperImage(UserImage user_image);
    void insertUserUrl(String name,String github_url,String naver_url,String tistory_url);
    void insertUserUrlInfo(String name,String title,String data);
}