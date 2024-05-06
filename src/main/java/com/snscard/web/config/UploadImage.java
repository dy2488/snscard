package com.snscard.web.config;

import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor
public class UploadImage {
    public ResponseEntity<Resource> getImage(String path) throws IOException {
        Path imagePath = Paths.get(path);
        Resource resource = new UrlResource(imagePath.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("Could not read the image: " + path);
        }
        return ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .header("Content-Type","image/jpeg")
                .body(resource);


/*
   로컬 파일 base64로 프론트엔트에서 제출 방식
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            byte[] imageData= new byte[(int) file.length()];
            fis.read(imageData);
            fis.close();
            String s = Base64.getEncoder().encodeToString(imageData);
            return s;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

*/

    }
}