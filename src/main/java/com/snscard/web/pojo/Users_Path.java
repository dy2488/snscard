package com.snscard.web.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Users_Path {
    private String name;
    private String image_path;
    private String url_path;
    public Users_Path(String name, String image_path) {
        this.name = name;
        this.image_path = image_path;
    }
    public Users_Path(String name, String image_path, String url_path) {
        this.name = name;
        this.image_path = image_path;
        this.url_path = url_path;
    }
    public Users_Path(String name) {
        this.name = name;
    }
}
