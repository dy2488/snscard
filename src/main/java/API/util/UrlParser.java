package API.util;
import java.net.URL;
public class UrlParser
{
    public String parseUrl(String sourceUrl,int partNum) throws Exception // Url을 파싱하여 사용자 이름을 가져오는 함수
    {
        URL url = new URL(sourceUrl);
        String path = url.getPath();
        String[] parts = path.split("/");
        return parts[partNum];
    }
}
