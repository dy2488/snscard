package com.snscard.web.service;

import com.snscard.web.utils.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface InformationService {
    String addUserAnswer(String a1, String a2, String a3, String a4, String a5) throws Exception;
    ResponseEntity<Resource> getUserImage() throws IOException;
    Result_Image CropperImage(int x1, int y1, int x3, int y3) throws IOException;
    ResponseEntity<Resource> getImageCropper() throws IOException;
    GetImageList queryAllUserImage(int number) throws IOException;
    GetImageList queryAllUserCropperImage(int number) throws IOException;
    Object addUserUrl(String github_url, String naver_url, String tistory_url,int cardNum) throws Exception;
    Result_Info addUserInfo(int cardNum, String info, int templateNum, String imageUrl, int x1, int y1, int x3, int y3) throws IOException;
    Result_Info queryUserInfo(int cardNum);
    Result_Image querySampleUrl();

}

