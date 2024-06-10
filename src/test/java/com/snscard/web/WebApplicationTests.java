package com.snscard.web;

import API.api.TistoryAPI;
import API.dto.Post;
import com.snscard.web.GetGithub.GetGithubInfo;
import com.snscard.web.GetNaver.GetNaverInfo;
import com.snscard.web.config.GetTitleAndDate;
import com.snscard.web.utils.GetTitleDate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WebApplicationTests {

    @Test
    void githubTest() throws Exception {
        GetTitleDate githubInfo = new GetGithubInfo().getGithubInfo("https://github.com/dy2488/");
        System.out.println(githubInfo);
    }


    @Test
    void NaverTest() throws Exception {
        List<Post> naverInfo = new GetNaverInfo().getNaverInfo("https://blog.naver.com/onlyyour486");
        Post post = naverInfo.get(0);
        String title = new GetTitleAndDate().extractValue(String.valueOf(post), "title");
        String data = new GetTitleAndDate().extractValue(String.valueOf(post), "date");
        System.out.println(title);
        System.out.println(data);
    }

    @Test
    void TistoryAPI() throws Exception {
        List<Post> tistoryAPI = new TistoryAPI().getTistoryAPI("https://holika.tistory.com/");
        System.out.println(tistoryAPI);
    }

}