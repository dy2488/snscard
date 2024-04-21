package com.snscard.web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Users {
    private String name;
    private String password;
    private String salt;
    public Users(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public Users(String name, String password, String salt) {
        this.name = name;
        this.password = password;
        this.salt = salt;
    }
}
