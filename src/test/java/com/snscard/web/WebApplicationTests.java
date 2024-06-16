package com.snscard.web;

import com.google.gson.Gson;
import com.snscard.web.DalleAPI.Generate_Image;
import com.snscard.web.GetGithub.GetGithubInfo;
import com.snscard.web.GetNaver.GetNaverInfo;
import com.snscard.web.GetTistory.GetTistoryInfo;
import com.snscard.web.config.GetUrlInfo;
import com.snscard.web.utils.GetTitleDate;
import com.snscard.web.utils.Result_Url_Info;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

//@SpringBootTest
class WebApplicationTests {
    private String imageUrl;
    @Test
    void imageUrlTest() throws Exception {


//        String image = new Generate_Image().getImage("비지니스 미팅", "전문적이고 신뢰할 수 있는 인상", "간단한 스타일", "강한 톤", "태양");
//
//        JSONObject jsonObject = new JSONObject(image);
//        JSONArray data = jsonObject.getJSONArray("data");
//        for(int i=0;i<data.length();i++){
//            JSONObject item= data.getJSONObject(i);
//            imageUrl= item.getString("url");
//        }
//        System.out.println(imageUrl);
//    }

    }
}