package com.snscard.web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagePath {
    private String name;
    private String image_all_name;
    private int cardNum=0;
    public ImagePath(String name,String image_all_name)
    {
        this.name=name;
        this.image_all_name=image_all_name;
    }
}
