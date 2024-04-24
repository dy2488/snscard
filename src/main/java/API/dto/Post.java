package API.dto;

public class Post //게시물정보 캡슐화한 클래스
{
    String title;
    int date;
    String img_url;

    public Post(String title, int date, String img_url)
    {
        this.title = title;
        this.date = date;
        this.img_url = img_url;
    }
    public Post(String title, int date)
    {
        this.title = title;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Date: " + date +", img_url: " + img_url;
    }

}
