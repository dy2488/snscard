package com.snscard.web.DalleAPI;

import API.api.DalleAPI;
import org.springframework.stereotype.Component;

@Component
public class Generate_Image {
    String apiKey="";
    String authKey="";
    public String getImage(String q1,String q2,String q3,String q4,String q5) throws Exception {
       return new DalleAPI().getDalleAPI(q1,q2,q3,q4,q5,apiKey,authKey);
    }
}
