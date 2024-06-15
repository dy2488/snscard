package com.snscard.web.UrlApi;

import com.snscard.web.utils.GetTitleDate;

public interface Url {
    GetTitleDate getInfo(String url)throws Exception;
}
