package API.api;
import API.UrlParser;
import API.dto.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class GithubAPI
{
    private static final String Github_Url = "https://api.github.com/users/";
    public GithubAPI()
    {
    }
    public List<Post> getGithubAPI(String sourceUrl) throws Exception
    {
        UrlParser u = new UrlParser();
        String userName = u.parseUrl(sourceUrl,1);
        String requestUrl = Github_Url + userName +"/repos";
        String response = requestHttp(requestUrl);
        List<Post> result = parseGithubJson(response);
        return result;
    }
    public List<Post> parseGithubJson(String jsonData)
    {
        JSONArray repos = new JSONArray(jsonData);
        List<Post> postList = new ArrayList<>();
        String[][] result = new String[repos.length()][2];
        for(int i=0;i<repos.length();i++)
        {
            JSONObject repo = repos.getJSONObject(i);
            String name = repo.getString("name");
            String updatedAt = repo.getString("updated_at");

            int tIndex = updatedAt.indexOf('T');
            String subString = updatedAt.substring(0, tIndex);
            String dateString = subString.replace("-","");
            int date = Integer.parseInt(dateString);

            postList.add(new Post(name,date));
        }
        return postList;
    }
    public String requestHttp(String requestUrl) throws IOException, InterruptedException {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(requestUrl))
                    .GET() // GET 메서드를 사용
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
}
