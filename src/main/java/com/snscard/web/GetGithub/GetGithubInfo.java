package com.snscard.web.GetGithub;

import API.api.GithubAPI;
import API.dto.Post;
import com.snscard.web.config.GetTitleAndDate;
import com.snscard.web.config.TitleDateTool;
import com.snscard.web.utils.GetTitleDate;

import java.util.List;

public class GetGithubInfo {
    public GetTitleDate getGithubInfo(String url) throws Exception {
        List<Post> githubApi = new GithubAPI().getGithubApi(url);
        Post post = githubApi.get(0);
        return new TitleDateTool().getTitleDate(String.valueOf(post));

    }
}
