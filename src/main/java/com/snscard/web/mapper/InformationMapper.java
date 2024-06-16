package com.snscard.web.mapper;

import com.snscard.web.pojo.*;
import com.snscard.web.utils.GetTitleDate;
import com.snscard.web.utils.ResultInfoAndImage;
import com.snscard.web.utils.ResultNameInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InformationMapper {
    void insertUserPath(ImagePath imagePath);

    List<String> queryAllUserImage(UserImage user_image);


    List<String> queryAllUserCropperImage(UserImage user_image);

    void insertGitHubUrl(UserGitHubUrl userUrl);
    void insertNaverUrl(UserNaverUrl userUrl);
    void insertTistoryUrl(UserTistoryUrl userUrl);

    void updateGithubUrl(UserGitHubUrl userUrl);
    void updateNaverUrl(UserNaverUrl userUrl);
    void updateTistoryUrl(UserTistoryUrl userUrl);



    void insertUserInfo(UserInfo userInfo);

    ResultNameInfo queryUserInfo(NameCardNum nameCardNum);
    String queryInfo(NameCardNUmTemplateNum nameCardNUmTemplateNum);
    void updateUserInfo(UserInfo userInfo);

    void insertUserCropPath(ImagePath imagePath);
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