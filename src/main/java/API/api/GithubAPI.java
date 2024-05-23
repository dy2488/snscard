package API.api;
import API.util.UrlParser;
import API.dto.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

public class GithubAPI
{
    private static final String GithubUrl = "https://api.github.com/users/";
    public GithubAPI()
    {
    }
    public List<Post> getGithubApi(String sourceUrl) throws Exception //호출되는 메서드
    {
        UrlParser u = new UrlParser();
        String userName = u.parseUrl(sourceUrl,1); //받은 URL에서 사용자 정보 획득 (주어진 URL의 첫번째 영역 가져옴)
        String requestUrl = GithubUrl + userName +"/repos"; //요청 URL 제작
        String response = requestHttp(requestUrl); //요청
        List<Post> result = parseGithubJson(response);
        return result;
    }
    private List<Post> parseGithubJson(String jsonData) //받은 JSON 파싱 후 배열로 저장하는 함수
    {
        JSONArray repos = new JSONArray(jsonData);
        List<Post> postList = new ArrayList<>();
        for(int i=0;i<repos.length();i++)
        {
            JSONObject repo = repos.getJSONObject(i); //가져온 정보를 저장
            String name = repo.getString("name");
            String updatedAt = repo.getString("updated_at");

            int tIndex = updatedAt.indexOf('T'); //updatedAt을 int로 변환하는 과정
            String subString = updatedAt.substring(0, tIndex);
            String dateString = subString.replace("-","");
            int date = Integer.parseInt(dateString);

            postList.add(new Post(name,date)); //객체배열 삽입
        }

        Collections.sort(postList, Comparator.comparingInt(Post::getDate).reversed()); //내림차순 정렬 후 최대 3개의 게시물 정보를 저장한 새로운 배열 리턴
        int toIndex = Math.min(postList.size(), 3);
        List<Post> firstThreePostList = postList.subList(0, toIndex);

        return firstThreePostList;
    }
    private String requestHttp(String requestUrl) throws IOException, InterruptedException //HTTP 요청 함수
    {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(requestUrl))
                    .GET() // GET 메서드를 사용
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body(); //응답받은 JSON 리턴
        }
}
