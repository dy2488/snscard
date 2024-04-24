package API;

import API.api.GithubAPI;
import API.dto.Post;

import java.util.List;

public class TestAPI
{
    public static void main(String[] args) throws Exception {
        //Dalle test
        /*
        String imgUrl;
        DalleAPI d = new DalleAPI();
        imgUrl = d.getDalleAPI("simple background","{APIKEY}");
        System.out.println(imgUrl);
        */

        //GITAPi test
        GithubAPI g = new GithubAPI();
        List<Post> temp = g.getGithubApi("https://github.com/yjpark0307/"); //파라터 형식은 되도록 지켜주기 바람
        System.out.println(temp);



        //parseUrl test
        /*
        UrlParser u = new UrlParser();
        System.out.println(u.parseUrl("https://github.com/yjpark0307/",1));
        */


    }
}
