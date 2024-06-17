package com.snscard.web.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result_Url_And_Info {
    private int code;
    private GetTitleDateUrl github;
    private GetTitleDateUrl naver;
    private GetTitleDateUrl tistory;
}
