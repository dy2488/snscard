package com.snscard.web.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result_Code_Msg {
    private int code;
    private String msg;
}
