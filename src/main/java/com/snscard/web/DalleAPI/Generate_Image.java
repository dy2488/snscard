package com.snscard.web.DalleAPI;

import API.api.DalleAPI;
import org.springframework.stereotype.Component;

@Component
public class Generate_Image {
    String apiKey="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";
    String authKey="151da27f-0f74-4dde-94fd-bceea586ea23:fx";
    public String getImage(String q1,String q2,String q3,String q4,String q5) throws Exception {
       return new DalleAPI().getDalleAPI(q1,q2,q3,q4,q5,apiKey,authKey);
    }
}
