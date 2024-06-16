package com.snscard.web.config;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class SaveImage {
    private String image;


    public void save(String imageUrl,String imageAllName,String path) throws IOException {
        JSONObject jsonObject = new JSONObject(imageUrl);
        JSONArray data = jsonObject.getJSONArray("data");
        for(int i=0;i<data.length();i++){
            JSONObject item= data.getJSONObject(i);
            image= item.getString("url");
        }
        URL url = new URL(image);
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(path+imageAllName);
        byte[] buffer = new byte[1024];
        int bytesRead ;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }
        fileOutputStream.close();
        inputStream.close();
    }

}
