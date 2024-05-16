package com.snscard.web.config;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public class UploadUserImages {

    public ResponseEntity<List<String>> uploadUserImages(List<String> path) throws IOException {
        return ResponseEntity.ok(path);

    }
}
