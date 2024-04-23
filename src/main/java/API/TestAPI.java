package API;

import API.api.GithubAPI;

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
        String[][] temp = g.getGithubAPI("https://github.com/yjpark0307/");
        System.out.println(temp[0][0] + " " +temp[0][1]);
        System.out.println(temp[1][0] + " " +temp[1][1]);



        //parseUrl test
        /*
        UrlParser u = new UrlParser();
        System.out.println(u.parseUrl("https://github.com/yjpark0307/",1));
        */


    }
}
