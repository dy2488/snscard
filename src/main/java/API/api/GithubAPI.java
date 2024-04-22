package API.api;
import java.net.URL;
import java.net.MalformedURLException;
public class GithubAPI
{
    private static final String Github_URL = "https://api.github.com/users/";

    public String[][] getGithubAPI(String URL)
    {
        String[][] temp = {};
        return temp;
    }

    public String parseGithubURL(String URL)
    {
        String username ="";
        try {
            URL url = new URL(URL);
            String path = url.getPath();
            if (path.length() > 1) { // 경로가 '/' 이후에 문자를 포함하고 있는지 확인
                username = path.substring(1); // 첫 번째 '/' 이후의 문자열을 추출
            }
            if (username.endsWith("/")) {
                username = username.substring(0, username.length() - 1); // 마지막 '/' 제거
            }
        } catch (MalformedURLException e) {
            System.out.println("Invalid URL: " + URL);
        }
        return username;
    }
}
