package API.api;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.math.*;

import API.util.AnswerMapper;
import com.deepl.api.DeepLException;
import org.json.JSONObject;

public class DalleAPI //이미지생성 AI 관련 클래스 입니다. 기본적으로 5개정도를 생성했었을때 0.1$의 비용이 듭니다. 현재 계정에는 10$가 충전되어있어 테스트 목적으로 넉넉히 사용 가능하나, 불필요하게 막쓰진 말아주세요.
{
    // APIkey를 그냥 노출한채로는 COMMIT이 불가합니다.
    private static final String DalleUrl = "https://api.openai.com/v1/images/generations";

    public String getDalleAPI(String q1,String q2,String q3,String q4, String q5, String apiKey,String authKey) throws Exception //prompt를 입력하여 호출하면, 해당 프롬프트 대로 이미지를 생성후, 이미지 URL을 반환하는 함수
    {
        String prompt,imgUrl;
        prompt = generatePrompt(q1,q2,q3,q4,q5,authKey);
        imgUrl = requestHttp(prompt,apiKey);
        return imgUrl;
    }
    private String generatePrompt(String q1,String q2,String q3,String q4, String q5, String authKey) throws DeepLException, InterruptedException {
        AnswerMapper answermapper = new AnswerMapper(authKey);
        int revisedQ1 = answermapper.getRevisedWeight(q1,1);
        int revisedQ2 = answermapper.getRevisedWeight(q2,2);
        int revisedQ3 = answermapper.getRevisedWeight(q3,3);
        String revisedQ4 = answermapper.getRevisedAnswer(q4,4);
        String revisedQ5 = answermapper.getRevisedAnswer(q5,5);// 번역

        int promptSetNumber = selectPromptSetNumber(revisedQ1,revisedQ2,revisedQ3);
        String prompt,defaultPrompt;
        int promptNumber;
        Random random = new Random();
        defaultPrompt = "A line art image of a [{object}] silhouette, isolated on a soft {tones} background. The silhouette is elegantly and simply drawn, emphasizing the graceful contours of the subjects's figure. The background's {pastel tones} are muted and harmonious, providing a gentle contrast to the crisp line art of the silhouette. This artwork is minimalist and stylish, focusing on the beauty of simplicity and the elegance of the subject's form.";
        switch(promptSetNumber)
        {
            case 1:
            {
                promptNumber = random.nextInt(1) + 1;
                if(promptNumber==1){
                    prompt = "A line art image of a [{object}] silhouette, isolated on a soft {tones} background. The silhouette is elegantly and simply drawn, emphasizing the graceful contours of the subjects's figure. The background's {pastel tones} are muted and harmonious, providing a gentle contrast to the crisp line art of the silhouette. This artwork is minimalist and stylish, focusing on the beauty of simplicity and the elegance of the subject's form.";
                }
                else
                {
                    prompt=defaultPrompt;
                }
                break;
            }
            case 2:
            {
                promptNumber = random.nextInt(1) + 1;
                if(promptNumber==1){
                    prompt = "a flat vector illustration depicting smooth stone textures that features {tones} using a simple modern style with emphasis on {object}.";
                }
                else
                {
                    prompt=defaultPrompt;
                }
                break;
            }
            case 3:
            {
                promptNumber = random.nextInt(1) + 1;
                if(promptNumber==1){
                    prompt = "a flat vector illustration depicting smooth stone textures that features {tones} using a simple modern style with emphasis on {object}.";
                }
                else
                {
                    prompt=defaultPrompt;
                }
                break;
            }
            case 4:
            {
                promptNumber = random.nextInt(1) + 1;
                if(promptNumber==1){
                    prompt = "a flat vector illustration depicting sandy beach that features {tones} using a modern style minimalistic with no specific border with emphasis on {object}.";
                }
                else
                {
                    prompt=defaultPrompt;
                }
                break;
            }
            case 5:
            {
                promptNumber = random.nextInt(1) + 1;
                if(promptNumber==1){
                    prompt = "a flat abstract art depicting sandy beach textures that features {tones} using a minimalist modern style with no specific border with emphasis on {object}.";
                }
                else
                {
                    prompt=defaultPrompt;
                }
                break;
            }
            default :{
                prompt = "";
            }
        }//style,texture,tone
        prompt = prompt.replace("{tones}",revisedQ4);
        prompt = prompt.replace("{object}",revisedQ5);
        System.out.println(prompt);
        return prompt;
    }

    private String selectPrompt(String tones, String object)
    {
        return "";
    }


    private int selectPromptSetNumber(int revisedQ1,int revisedQ2, int revisedQ3)
    {
        int averageWeight = (revisedQ1 + revisedQ2 + revisedQ3)/3;

        Random random = new Random();
        int promptSetNumber;
        if(averageWeight == 1)
        {
            promptSetNumber = random.nextInt(2) + 1;
        }
        else if (averageWeight == 5) {
            promptSetNumber = random.nextInt(2) + 4;
        }
        else
        {
            promptSetNumber = random.nextInt(3) + (averageWeight-1);
        }
        return promptSetNumber;
    }

    private String randomTexture()
    {
        String[] textureList = {"smooth stone","sandy beach", "polished wood", "soft velvet"};
        Random random = new Random();
        int index = random.nextInt(textureList.length);
        return textureList[index];
    }
    private String randomShape()
    {
        String[] shapeList = {"with no specific border","with filling the entire canvas"};
        Random random = new Random();
        int index = random.nextInt(shapeList.length);
        return shapeList[index];
    }

    private String randomPattern()
    {
        String[] shapeList = {"line pattern","square pattern","circle pattern"};
        Random random = new Random();
        int index = random.nextInt(shapeList.length);
        return shapeList[index];
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