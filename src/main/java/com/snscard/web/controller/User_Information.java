package com.snscard.web.controller;

import com.snscard.web.service.InformationService;
import com.snscard.web.utils.GetImageList;
import com.snscard.web.utils.Result_Image;
import com.snscard.web.utils.Result_Information;
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

    @RequestMapping ("all/{id}")
    public Result_Information queryInformation(@PathVariable String id) {
        return informationService.getInformation(id);
    }
    @RequestMapping("addInfo")
    public Result_Information addInformation(String username,String name_us,String tel,String address,String vocation,String company,String email,String introduction) {
        return informationService.insertInformation(username,name_us,tel,address,vocation,company,email,introduction);
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
}
