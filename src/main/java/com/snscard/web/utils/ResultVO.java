package com.snscard.web.utils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultVO {
    private int code;
    private String msg;
    private String user;
    public ResultVO(int code, String msg, String user) {
        this.code = code;
        this.msg = msg;
        this.user = user;
    }
    public ResultVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
