package com.snscard.web.GetTistory;

import API.api.TistoryAPI;
import API.dto.Post;

import java.util.List;

public class GetTistoryInfo {
    public List<Post> getTistoryInfo(String url) throws Exception {
        return new TistoryAPI().getTistoryAPI(url);
    }
}
