package API.api;
import API.UrlParser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GithubAPI
{
    private static final String Github_Url = "https://api.github.com/users/";
    public GithubAPI()
    {
    }
    public String[][] getGithubAPI(String sourceUrl) throws Exception
    {
        UrlParser u = new UrlParser();
        String userName = u.parseUrl(sourceUrl,1);
        String requestUrl = Github_Url + userName +"/repos";
        System.out.println(requestUrl);
        String response = requestApi(requestUrl);
        System.out.println(response);
        String[][] temp = parseGithubJson(response);
        return temp;
    }
    public String[][] parseGithubJson(String jsonData)
    {
        JSONArray repos = new JSONArray(jsonData);

        String[][] result = new String[repos.length()][2];
        for(int i=0;i<repos.length();i++)
        {
            JSONObject repo = repos.getJSONObject(i);
            String name = repo.getString("name");
            String updatedAt = repo.getString("updated_at");
            System.out.println(name + "" +updatedAt);
            result[i][0] = name;
            result[i][1] = updatedAt;
        }
        return result;
    }
    public String requestApi(String requestUrl) throws IOException, InterruptedException {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(requestUrl))
                    .GET() // GET 메서드를 사용
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }
}
