package com.snscard.web.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultInfoAndImage {
    private String name,info,imageUrl;
    private int cardNum,templateNum,x1,x3,y1,y3;
    public ResultInfoAndImage(String name, int cardNum, String info, String imageUrl, int templateNum) {
        this.name=name;
        this.cardNum=cardNum;
        this.info=info;
        this.imageUrl=imageUrl;
        this.templateNum=templateNum;
    }
}
