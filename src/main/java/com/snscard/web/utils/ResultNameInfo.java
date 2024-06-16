package com.snscard.web.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultNameInfo {
    private String name;
    private int cardNum;
    private String info;
    private int templateNum;
}
