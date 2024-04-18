package API.openai;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class DalleAPI //이미지생성 AI 관련 클래스 입니다. 기본적으로 5개정도를 생성했었을때 0.1$의 비용이 듭니다. 현재 계정에는 10$가 충전되어있어 테스트 목적으로 넉넉히 사용 가능하나, 불필요하게 막쓰진 말아주세요.
{
    // APIkey를 그냥 노출한채로는 COMMIT이 불가합니다.
    private static final String API_URL = "https://api.openai.com/v1/images/generations";

    public String getDalleAPI(String prompt,String apiKey) throws Exception //prompt를 입력하여 호출하면, 해당 프롬프트 대로 이미지를 생성후, 이미지 URL을 반환하는 함수
    {
        HttpClient client = HttpClient.newHttpClient();
        JSONObject data = new JSONObject();
        data.put("prompt", prompt);
        data.put("n", 1);
        data.put("size", "1024x1024");
        data.put("model", "dall-e-2");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(data.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body(); //이미지 URL을 반환
    }
}