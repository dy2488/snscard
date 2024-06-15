package com.snscard.web;

import com.google.gson.Gson;
import com.snscard.web.DalleAPI.Generate_Image;
import com.snscard.web.GetGithub.GetGithubInfo;
import com.snscard.web.GetNaver.GetNaverInfo;
import com.snscard.web.GetTistory.GetTistoryInfo;
import com.snscard.web.config.GetUrlInfo;
import com.snscard.web.utils.GetTitleDate;
import com.snscard.web.utils.Result_Url_Info;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

//@SpringBootTest
class WebApplicationTests {
    @Test
    void contextLoads() throws Exception {
        String q1,q2,q3,q4,q5; //q1..은 아무거나 막 적은거라 실제로 들어갈 답변이랑은 매우 다릅니다
        q1="비지니스 미팅"; // q1답변
        q2="전문적이고 신뢰할 수 있는 인상"; //q2 답변
        q3="간단한 스타일"; //q3 답변
        q4="옅은 톤"; //q4 답변
        q5="고양이"; //q5 답변
        String image = new Generate_Image().getImage(q1, q2, q3, q4, q5);
        System.out.println(image);
    }
    @Test
    void githubTest() throws Exception{
        GetGithubInfo githubInfo = new GetGithubInfo();
        GetNaverInfo naverInfo = new GetNaverInfo();
        GetTistoryInfo tistoryInfo = new GetTistoryInfo();
        GetTitleDate github = new GetUrlInfo().getUrl("https://github.com/dy24889/", githubInfo);
        GetTitleDate naver = new GetUrlInfo().getUrl("", naverInfo);
        GetTitleDate tistory = new GetUrlInfo().getUrl("https://holika.tistory.com/", tistoryInfo);
        if(github!=null && "0".equals(github.getDate()))
        {
            System.out.println("github");
        }
        if(naver!=null&& "0".equals(naver.getDate()))
        {
            System.out.println("naver");
        }
        if(tistory!=null&& "0".equals(tistory.getDate()))
        {
            System.out.println("tistory");
        }
        else {
            Result_Url_Info resultUrlInfo = new Result_Url_Info(3001, new GetTitleDate(github.getTitle(), github.getDate()), new GetTitleDate(naver.getTitle(), naver.getDate()), new GetTitleDate(tistory.getTitle(), tistory.getDate()));
            System.out.println(new Gson().toJson(resultUrlInfo));
        }
    }
    @Test
    void getScoresTest() {
        String x1="h";
        String x2="o";
    }

}