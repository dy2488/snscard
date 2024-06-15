package com.snscard.web.GetTistory;

import API.api.TistoryAPI;
import API.dto.Post;
import com.snscard.web.UrlApi.Url;
import com.snscard.web.config.TitleDateTool;
import com.snscard.web.utils.GetTitleDate;

import java.util.List;

public class GetTistoryInfo implements Url {
    public GetTitleDate getInfo(String url) throws Exception {
        List<Post> tistoryAPI = new TistoryAPI().getTistoryAPI(url);
        Post post = tistoryAPI.get(0);
        return new TitleDateTool().getTitleDate(String.valueOf(post));
    }
}
