package com.snscard.web.service;

import com.snscard.web.utils.*;

import java.io.IOException;

public interface InformationService {
    Result_Image addUserAnswer(String a1, String a2, String a3, String a4, String a5,int cardNum) throws Exception;
    GetImageList queryAllUserImage(int number) throws IOException;
    GetImageList queryAllUserCropperImage(int number) throws IOException;
    Object addUserUrl(String github_url, String naver_url, String tistory_url,int cardNum) throws Exception;
    Object addUserInfo(int cardNum, String info, int templateNum, String imageUrl, int x1, int y1, int x3, int y3) throws IOException;
    Object queryUserInfo(String id,int cardNum);
    Object updateUserInfo(int cardNum,String info,int templateNum);
    Result_Image querySampleUrl();
    Result_Url_And_Info queryUserUrlInfo(int number) throws Exception;

}

