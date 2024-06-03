package com.snscard.web;

import API.api.NaverAPI;
import API.api.TistoryAPI;
import API.dto.Post;
import com.snscard.web.github.GenerateGithubApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WebApplicationTests {

    @Test
    void githubTest() throws Exception {
        GenerateGithubApi generateGithubApi = new GenerateGithubApi();
        List<Post> githubApiUrl = generateGithubApi.getGithubApiUrl();
        System.out.println(githubApiUrl);
    }

    @Test
    void NaverTest() throws Exception {
        List<Post> naverAPI = new NaverAPI().getNaverAPI("https://blog.naver.com/springboot");
        System.out.println(naverAPI);
    }

    @Test
    void TistoryAPI() throws Exception {
        List<Post> tistoryAPI = new TistoryAPI().getTistoryAPI("https://holika.tistory.com/");
        System.out.println(tistoryAPI);
    }

}
