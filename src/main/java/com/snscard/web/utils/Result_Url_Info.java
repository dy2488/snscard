package com.snscard.web.utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result_Url_Info {
    private int code;
    private GetTitleDate github;
    private GetTitleDate naver;
    private GetTitleDate tistory;
}
