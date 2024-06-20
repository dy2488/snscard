package com.snscard.web.controller;
import com.snscard.web.service.InformationService;
import com.snscard.web.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value="info")
@Controller
public class User_Information {
    private final InformationService informationService;

    @Autowired
    public User_Information(InformationService informationService) {
        this.informationService = informationService;
    }

    @RequestMapping("searchUser")
    public Object searchUserInfo(String id,int cardNum) {
       return null;
    }


    @RequestMapping("addAnswer")
    public Result_Image addUserAnswer(String a1, String a2, String a3, String a4, String a5,int cardNum) throws Exception {
        return informationService.addUserAnswer(a1,a2,a3,a4,a5,cardNum);
    }
    @RequestMapping("getAllImage")
    public GetImageList queryAllUserImage(int number) throws IOException {
        return informationService.queryAllUserImage(number);
    }
    @RequestMapping("getAllCropperImage")
    public GetImageList queryAllUserCropperImage(int number) throws IOException {
        return informationService.queryAllUserCropperImage(number);
    }
    @RequestMapping("insertInfo")
    public Object addUserInfo(int cardNum, String info, int templateNum, String imageUrl, int x1, int y1, int x3, int y3) throws IOException {
        return informationService.addUserInfo(cardNum, info, templateNum, imageUrl, x1, y1, x3, y3);
    }
    @RequestMapping("getUserInfo")
    public Object queryUserInfo(@RequestParam(required = false) String id,int cardNum) {
        return informationService.queryUserInfo(id,cardNum);
    }
    @RequestMapping("insertUrl")
    public Object insertUserUrl(@RequestParam(required = false)String github_url, @RequestParam(required = false) String naver_url, @RequestParam(required = false) String tistory_url,int cardNum) throws Exception {
       return informationService.addUserUrl(github_url,naver_url,tistory_url,cardNum);
    }
    @RequestMapping("getSampleUrl")
    public Result_Image querySampleUrl()
    {
        return informationService.querySampleUrl();
    }
    @RequestMapping("getUserUrlInfo")
    public Result_Url_And_Info queryUserUrlInfo(int cardNum) throws Exception {
        return informationService.queryUserUrlInfo(cardNum);
    }
    @RequestMapping("modifyUserInfo")
    public Object updateUerInfo(int cardNum,String info,int templateNum)
    {
        return informationService.updateUserInfo(cardNum, info, templateNum);
    }
}
