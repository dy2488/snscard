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

        //GIT test


        GithubAPI g = new GithubAPI();
        List<Post> temp = g.getGithubAPI("https://github.com/yjpark0307/");
        System.out.println(temp);



        //parseUrl test
        /*
        UrlParser u = new UrlParser();
        System.out.println(u.parseUrl("https://github.com/yjpark0307/",1));
        */


    }
}
