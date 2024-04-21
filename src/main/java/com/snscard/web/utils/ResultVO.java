package com.snscard.web.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultVO {
    private int code;
    private String msg;
    private String name;
    public ResultVO(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.name = name;
    }
    public ResultVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
