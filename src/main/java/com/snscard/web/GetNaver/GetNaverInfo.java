package com.snscard.web.GetNaver;

import API.api.NaverAPI;
import API.dto.Post;

import java.util.List;

public class GetNaverInfo {
    public List<Post> getNaverInfo(String url) throws Exception {
        return new NaverAPI().getNaverAPI(url);
    }
}
