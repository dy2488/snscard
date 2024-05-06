package com.snscard.web.service;

import com.snscard.web.utils.Result_Image;
import com.snscard.web.utils.Result_Information;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface InformationService {
    Result_Information getInformation(String id);
    Result_Information insertInformation(String username,String name_us,String tel,String address,String vocation,String company,String email,String introduction);
    Result_Image addUserAnswer(String a1, String a2, String a3, String a4, String a5) throws Exception;
    ResponseEntity<Resource> getUserImage() throws IOException;
    Result_Image CropperImage(int x1, int y1, int x3, int y3) throws IOException;
    ResponseEntity<Resource> getImageCropper() throws IOException;
}

