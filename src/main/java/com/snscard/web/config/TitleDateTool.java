package com.snscard.web.config;

import com.snscard.web.utils.GetTitleDate;

public class TitleDateTool {
    public GetTitleDate getTitleDate(String post) {
        return new GetTitleDate(new GetTitleAndDate().extractValue(post,"title"),new GetTitleAndDate().extractValue(post,"date") );
    }

}
