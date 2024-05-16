package com.snscard.web.config;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SaveImage {
    private String image;


    public void save(String imageUrl,String imageName,String path,String suffix) throws IOException {
        JSONObject jsonObject = new JSONObject(imageUrl);
        JSONArray data = jsonObject.getJSONArray("data");
        for(int i=0;i<data.length();i++){
            JSONObject item= data.getJSONObject(i);
            image= item.getString("url");
        }
        URL url = new URL(image);
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        Path path1 = Paths.get(path, imageName+suffix);
        Files.copy(inputStream, path1);
        inputStream.close();
    }

}
