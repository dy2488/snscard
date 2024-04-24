package API.dto;

public class Post
{
    String title;
    int time;
    String img_url;

    public Post(String title, int time, String img_url)
    {
        this.title = title;
        this.time = time;
        this.img_url = img_url;
    }
    public Post(String title, int time)
    {
        this.title = title;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Date: " + time +", img_url: " + img_url;
    }

}
