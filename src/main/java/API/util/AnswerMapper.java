package API.util;
import java.util.HashMap;
import java.util.Map;
import com.deepl.api.*;
public class AnswerMapper
{
    Map<String, Integer> q1Map = new HashMap<>();
    Map<String,Integer> q2Map = new HashMap<>();
    Map<String,Integer> q3Map = new HashMap<>();
    Map<String,String> q4Map = new HashMap<>();
    Map<String,String> q5Map = new HashMap<>();
    String authKey;
    public AnswerMapper(String authKey)
    {
        initialize();
        this.authKey = authKey;
    }
    private void initialize() //작을수록 깔끔함 //높을수록 복잡하고 추상적임
    {
        q1Map.put("비지니스 미팅", 1);
        q1Map.put("네트워킹 행사", 2);
        q1Map.put("고객 방문", 3);
        q1Map.put("친목", 5);

        q2Map.put("전문적이고 신뢰할 수 있는 인상", 1);
        q2Map.put("세련되고 고급스러운 인상", 2);
        q2Map.put("친근하고 접근하기 쉬운 인상", 3);
        q2Map.put("혁신적이고 창의적인 인상", 5);

        q3Map.put("간단한 스타일", 1);
        q3Map.put("일반 스타일", 2);
        q3Map.put("복잡한 스타일", 5);

        q4Map.put("파스텔 톤", "pastel tones");
        q4Map.put("선명한 톤", "vivid tones");
        q4Map.put("강한 톤", "strong tones");
        q4Map.put("밝은 톤", "bright tones");
        q4Map.put("옅은 톤", "pale tones");
        q4Map.put("부드러운 톤", "soft tones");
        q4Map.put("깊은 톤", "deep tones");
        q4Map.put("어두운 톤", "dark tones");
    }
    public int getRevisedWeight(String answer, int questionNumber) throws DeepLException, InterruptedException {
        int revisedWeight;
        switch (questionNumber)
        {
            case 1 : revisedWeight = q1Map.get(answer); break;
            case 2 : revisedWeight = q2Map.get(answer); break;
            case 3 : revisedWeight = q3Map.get(answer); break;
            default: revisedWeight = 1;
        }
        return revisedWeight;
    }
    public String getRevisedAnswer(String answer, int questionNumber) throws DeepLException, InterruptedException {
        String revisedAnswer;
        switch (questionNumber)
        {
            case 4 : revisedAnswer = q4Map.get(answer); break;
            case 5 : revisedAnswer = translateKorean(answer,authKey); break; //번역 해야함
            default: revisedAnswer = "";
        }
        return revisedAnswer;
    }

    private String translateKorean(String answer,String authKey) throws DeepLException, InterruptedException {
        Translator translator = new Translator(authKey);
        TextResult result = translator.translateText(answer,null,"en-US");
        String revisedResult = result.getText();
        return revisedResult;
    }
}
