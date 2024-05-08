package API.api;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

import org.json.JSONObject;

public class DalleAPI //이미지생성 AI 관련 클래스 입니다. 기본적으로 5개정도를 생성했었을때 0.1$의 비용이 듭니다. 현재 계정에는 10$가 충전되어있어 테스트 목적으로 넉넉히 사용 가능하나, 불필요하게 막쓰진 말아주세요.
{
    // APIkey를 그냥 노출한채로는 COMMIT이 불가합니다.
    private static final String DalleUrl = "https://api.openai.com/v1/images/generations";

    public String getDalleAPI(String q1,String q2,String q3,String q4, String q5, String apiKey) throws Exception //prompt를 입력하여 호출하면, 해당 프롬프트 대로 이미지를 생성후, 이미지 URL을 반환하는 함수
    {
        String prompt,imgUrl;
        int algorithmFlag=1;
        prompt = generatePrompt(q1,q2,q3,q4,q5,algorithmFlag);
        imgUrl = requestHttp(prompt,apiKey);
        return imgUrl;
    }
    private String generatePrompt(String q1,String impression,String style,String tone, String object, int algorithmFlag)
    {
        String prompt;
        String texture = randomTexture();
        switch(algorithmFlag)
    {
        case 1: prompt = "a " + style + " depicting " + texture + " textures that features " + tone + " using a " + q1 + " with emphasis on " + object + " emitting an aura of " + impression + "."; break;
        default : prompt = q1 + ", " + impression + ", " + style + ", " + tone + ", " + object;

    }
        return prompt;
    }

    private String randomTexture()
    {
        String[] textureList = {"smooth stone","sandy beach", "polished wood", "soft velvet"};
        Random random = new Random();
        int index = random.nextInt(textureList.length);
        return textureList[index];
    }

    private String requestHttp(String prompt, String apiKey) throws Exception
    {
        HttpClient client = HttpClient.newHttpClient();
        JSONObject data = new JSONObject();
        data.put("prompt", prompt);
        data.put("n", 1);
        data.put("size", "1024x1024");
        data.put("model", "dall-e-3");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DalleUrl))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(data.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body(); //이미지 URL을 반환
    }
}