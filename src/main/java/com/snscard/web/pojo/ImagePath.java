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
    private int cardNum;
}
