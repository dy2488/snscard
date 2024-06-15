package com.snscard.web.GetGithub;

import API.api.GithubAPI;
import API.dto.Post;
import com.snscard.web.UrlApi.Url;
import com.snscard.web.config.TitleDateTool;
import com.snscard.web.utils.GetTitleDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class GetGithubInfo implements Url {
    public GetTitleDate getInfo(String url) throws Exception {

        List<Post> githubApi = new GithubAPI().getGithubApi(url);
        Post post = githubApi.get(0);
        return new TitleDateTool().getTitleDate(String.valueOf(post));

    }
}
