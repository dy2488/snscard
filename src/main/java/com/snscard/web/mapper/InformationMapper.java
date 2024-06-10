package com.snscard.web.mapper;

import com.snscard.web.pojo.UserImage;
import com.snscard.web.pojo.UserGitHubUrl;
import com.snscard.web.utils.ResultInfoAndImage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InformationMapper {
    void insertUserPath(String name, String image_all_name, int cardNum);

    List<String> queryAllUserImage(UserImage user_image);

    void insertUserCropPath(String uuid, String name, String image_all_name);

    List<String> queryAllUserCropperImage(UserImage user_image);

    void insertGitHubUrl(UserGitHubUrl userUrl);
    void insertNaverUrl(UserGitHubUrl userUrl);
    void insertTistoryUrl(UserGitHubUrl userUrl);

    void insertUserUrlInfo(String name, String title, String data,int cardNum);

    void insertImageCoordinate(String name, int cardNum, int x1, int y1, int x3, int y3);

    void insertUserInfo(String name, int cardNum, String info, int templateNum);

    ResultInfoAndImage queryUserInfo(String name, int cardNum);

    void insertUserCropPath(String name, String imageAllName, int cardNum);

    String queryUserGithubInfo(int cardNum);
}