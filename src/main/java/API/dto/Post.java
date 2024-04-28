package API.dto;

public class Post //게시물정보 캡슐화한 클래스
{
    String title;
    int date;
    String imgUrl;

    public Post(String title, int date, String img_url)
    {
        this.title = title;
        this.date = date;
        this.imgUrl = imgUrl;
    }
    public Post(String title, int date)
    {
        this.title = title;
        this.date = date;
    }

    public String getTitle()
    {
        return title;
    }
    public int getDate()
    {
        return date;
    }
    public String getImgUrl()
    {
        return imgUrl;
    }
    @Override
    public String toString() {
        return "(title: " + title + ", date: " + date +", imgUrl: " + imgUrl + ")";
    }

}
