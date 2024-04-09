package com.snscard.web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//변수가 보함은 구조자
@NoArgsConstructor//변수 없은 구조자
public class User_Information {
    private String name;
    private String name_us;
    private String tel;
    private String address;
    private String vocation;
    private String company;
    private String email;
    private String introduction;
    private String photo_path;
}
