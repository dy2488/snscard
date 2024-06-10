package com.snscard.web.controller;

import com.snscard.web.service.InformationService;
import com.snscard.web.utils.GetImageList;
import com.snscard.web.utils.Result_Image;
import com.snscard.web.utils.Result_Info;
import com.snscard.web.utils.Result_Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping("addAnswer")
    public Result_Image addUserAnswer(String a1, String a2, String a3, String a4, String a5) throws Exception {
        return informationService.addUserAnswer(a1,a2,a3,a4,a5);
    }
    @RequestMapping("getImage")
    public ResponseEntity<Resource> getUserImage() throws IOException {
        return informationService.getUserImage();
    }
    @RequestMapping("CropperImage")
    public Result_Image CropperImage(int x1,int y1,int x3,int y3) throws IOException {
        return informationService.CropperImage(x1, y1, x3, y3);
    }
    @RequestMapping("getCropperImage")
    public ResponseEntity<Resource> getImageCropper() throws IOException {
        return informationService.getImageCropper();
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
    public Result_Info addUserInfo(int cardNum, String info, int templateNum, String imageUrl, int x1, int y1, int x3, int y3)  {
        return informationService.addUserInfo(cardNum, info, templateNum, imageUrl, x1, y1, x3, y3);
    }
    @RequestMapping("getUserInfo")
    public Result_Info queryUserInfo(int cardNum) {
        return informationService.queryUserInfo(cardNum);
    }
    @RequestMapping("insertUrl")
    public Result_Url insertUserUrl(@RequestParam(required = false)String github_url, @RequestParam(required = false) String naver_url, @RequestParam(required = false) String tistory_url,int cardNum) throws Exception {
       return informationService.addUserUrl(github_url,naver_url,tistory_url,cardNum);
    }
}
