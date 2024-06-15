package com.snscard.web.mapper;

import com.snscard.web.pojo.*;
import com.snscard.web.utils.GetTitleDate;
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
    void insertNaverUrl(UserNaverUrl userUrl);
    void insertTistoryUrl(UserTistoryUrl userUrl);

    void updateGithubUrl(UserGitHubUrl userUrl);
    void updateNaverUrl(UserNaverUrl userUrl);
    void updateTistoryUrl(UserTistoryUrl userUrl);


    void insertImageCoordinate(String name, int cardNum, int x1, int y1, int x3, int y3);

    void insertUserInfo(String name, int cardNum, String info, int templateNum);

    ResultInfoAndImage queryUserInfo(String name, int cardNum);

    void insertUserCropPath(String name, String imageAllName, int cardNum);
    String queryGithubUrl(NameCardNum nameCardNum); ;
    String queryNaverUrl(NameCardNum nameCardNum); ;
    String queryTistoryUrl(NameCardNum nameCardNum); ;
    GetTitleDate queryGithubInfo(NameCardNum nameCardNum); ;
    GetTitleDate queryNaverInfo(NameCardNum nameCardNum); ;
    GetTitleDate  queryTistoryInfo(NameCardNum nameCardNum); ;

    void insertGithubInfo(UserTitleDate userTitleDate);
    void insertNaverInfo(UserTitleDate userTitleDate);
    void insertTistoryInfo(UserTitleDate userTitleDate);

    void updateGithubInfo(UserTitleDate userTitleDate);
    void updateNaverInfo(UserTitleDate userTitleDate);
    void updateTistoryInfo(UserTitleDate userTitleDate);

    String querySampleUrl(int number);

}