package API.api;
import API.dto.Post;
import API.util.UrlParser;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.net.MalformedURLException;
import java.net.URL;
public class TistoryAPI
{
    private static final String TistoryUrl = "https://{userid}.tistory.com/rss";

    public List<Post> getTistoryAPI(String sourceUrl) throws Exception //호출되는 메서드
    {
        String userId = "";
        try {
            userId = extractUserId(sourceUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String requestUrl = TistoryUrl.replace("{userid}",userId); //요청 URL 제작
        Document response = requestRss(requestUrl); //요청
        List<Post> result = new ArrayList<>();
        if(response==null)
        {
            result.add(new Post("no post",0));
        }
        else if (response.getDocumentElement()!=null)
        {
            result = parseTistoryRss(response);
        }
        return result;
    }

    private List<Post> parseTistoryRss(Document doc) throws Exception
    {
        NodeList itemList = doc.getElementsByTagName("item");
        List<Post> postList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Node itemNode = itemList.item(i);

            if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                Element itemElement = (Element) itemNode;

                // <title> 요소와 <pubDate> 요소를 가져오기
                String title = itemElement.getElementsByTagName("title").item(0).getTextContent();
                String pubDate = itemElement.getElementsByTagName("pubDate").item(0).getTextContent();
                SimpleDateFormat originalFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
                SimpleDateFormat targetFormat = new SimpleDateFormat("yyyyMMdd");
                int finalDate;
                try {
                    Date date = originalFormat.parse(pubDate);
                    String formattedDate = targetFormat.format(date);
                    finalDate = Integer.parseInt(formattedDate);
                    postList.add(new Post(title,finalDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                    postList.add(new Post("no post",0));
                }
            }
        }
        return postList;
    }

    private String extractUserId(String sourceUrl) throws MalformedURLException
    {
        URL url = new URL(sourceUrl);

        String host = url.getHost();

        if (host.endsWith(".tistory.com")) {
            return host.substring(0, host.indexOf(".tistory.com"));
        } else {
            throw new MalformedURLException("Invalid tistory.com URL");
        }
    }

    private Document requestRss(String requestUrl)
    {
        Document doc = null;
        try
        {
            URL url = new URL(requestUrl);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(url.openStream());
            doc.getDocumentElement().normalize();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }


}
