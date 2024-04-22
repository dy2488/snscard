package API;

import API.api.GithubAPI;

public class TestAPI
{
    public static void main(String[] args) throws Exception {
        /*String imgUrl;
        DalleAPI d = new DalleAPI();
        imgUrl = d.getDalleAPI("simple background","");
        System.out.println(imgUrl);*/

        GithubAPI g = new GithubAPI();
        String temp = g.parseGithubURL("https://github.com/yjpark0307/");
        System.out.println(temp);


    }
}
