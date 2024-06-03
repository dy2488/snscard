package com.snscard.web.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUrlTitleData {
    private String url;
    private String title;
    private String Data;
}
