package com.snscard.web.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetTitleAndDate {
    public String extractValue(String tuple, String key) {
        String regex = key + ":\\s*([^,\\)]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tuple);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
}
