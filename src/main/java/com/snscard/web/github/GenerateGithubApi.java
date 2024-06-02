package com.snscard.web.github;

import API.api.GithubAPI;
import API.dto.Post;

import java.util.List;

public class GenerateGithubApi {
    public List<Post> getGithubApiUrl() throws Exception {
        return new GithubAPI().getGithubApi("https://github.com/dy2488/");
    }
}
