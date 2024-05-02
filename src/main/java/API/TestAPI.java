package API;

import API.api.DalleAPI;
import API.api.GithubAPI;
import API.dto.Post;

import java.util.List;

public class TestAPI
{
    public static void main(String[] args) throws Exception {
        //Dalle test
        /*
        String imgUrl,q1,q2,q3,q4,q5; //q1..은 아무거나 막 적은거라 실제로 들어갈 답변이랑은 매우 다릅니다
        q1="simple background"; // q1답변
        q2="abstract"; //q2 답변
        q3="beach"; //q3 답변
        q4="dog"; //q4 답변
        q5="people"; //q5 답변
        DalleAPI d = new DalleAPI();
        imgUrl = d.getDalleAPI(q1,q2,q3,q4,q5,apiKey);
        System.out.println(imgUrl);
         */

        /*
        //깃허브 최근 업데이트된 프로젝트 3개의 제목,업데이트일자 가져오기   //관련 클래스 : GithubAPI
        GithubAPI g = new GithubAPI();
        List<Post> temp = g.getGithubApi("https://github.com/dy2488/"); //파라미터는 사용자정보에 저장된 깃허브 링크를 그대로 넘겨주면 됨
        System.out.println(temp); //테스트 출력 알고리즘
         */


        //parseUrl test
        /*
        UrlParser u = new UrlParser();
        System.out.println(u.parseUrl("https://github.com/yjpark0307/",1));
        */


    }
}
