package com.snscard.web.GetNaver;
import API.api.NaverAPI;
import API.dto.Post;
import com.snscard.web.UrlApi.Url;
import com.snscard.web.config.TitleDateTool;
import com.snscard.web.utils.GetTitleDate;

import java.util.List;

public class GetNaverInfo implements Url {
    public GetTitleDate getInfo(String url) throws Exception {
        List<Post> naverAPI = new NaverAPI().getNaverAPI(url);
        Post post = naverAPI.get(0);
        return new TitleDateTool().getTitleDate(String.valueOf(post));
    }
}
