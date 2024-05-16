package API.util;
import java.util.HashMap;
import java.util.Map;
import com.deepl.api.*;
public class AnswerMapper
{
    Map<String,String> q1Map = new HashMap<>();
    Map<String,String> q2Map = new HashMap<>();
    Map<String,String> q3Map = new HashMap<>();
    Map<String,String> q4Map = new HashMap<>();
    Map<String,String> q5Map = new HashMap<>();
    String authKey;
    public AnswerMapper(String authKey)
    {
        initialize();
        this.authKey = authKey;
    }
    private void initialize()
    {
        q1Map.put("비지니스 미팅", "minimalist");
        q1Map.put("네트워킹 행사", "minimalist");
        q1Map.put("고객 방문", "simple");
        q1Map.put("친목", "maximalist");

        q2Map.put("전문적이고 신뢰할 수 있는 인상", "modern style");
        q2Map.put("세련되고 고급스러운 인상", "modern style");
        q2Map.put("혁신적이고 창의적인 인상", "impressionistic style");
        q2Map.put("친근하고 접근하기 쉬운 인상", "impressionistic style");

        q3Map.put("디지털 아트워크", "digital artwork");
        q3Map.put("추상 미술", "abstract art");
        q3Map.put("수채화 일러스트레이션", "watercolor illustration");
        q3Map.put("스케치", "sketch");
        q3Map.put("벡터 일러스트레이션", "vector illustration");

        q4Map.put("파스텔 톤", "pastel tones");
        q4Map.put("선명한 톤", "vivid tones");
        q4Map.put("강한 톤", "strong tones");
        q4Map.put("밝은 톤", "bright tones");
        q4Map.put("옅은 톤", "pale tones");
        q4Map.put("부드러운 톤", "soft tones");
        q4Map.put("깊은 톤", "deep tones");
        q4Map.put("어두운 톤", "dark tones");
    }
    public String getRevisedAnswer(String answer, int questionNumber) throws DeepLException, InterruptedException {
        String revisedAnswer;
        switch (questionNumber)
        {
            case 1 : revisedAnswer = q1Map.get(answer); break;
            case 2 : revisedAnswer = q2Map.get(answer); break;
            case 3 : revisedAnswer = q3Map.get(answer); break;
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
