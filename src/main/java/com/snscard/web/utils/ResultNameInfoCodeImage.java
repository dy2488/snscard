package com.snscard.web.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultNameInfoCodeImage {
    private int code;
    private String name;
    private int cardNum;
    private String info;
    private String generatedUrl;
    private String modifyUrl;
    private int templateNum;
}
