package com.snscard.web.utils;

import com.snscard.web.pojo.User_Information;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result_Information {
    private int code;
    private User_Information user;
}
