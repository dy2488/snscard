package com.snscard.web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameCardNUmTemplateNum {
    private String name;
    private int cardNum;
    private int templateNum;
}
