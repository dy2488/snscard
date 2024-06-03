package com.snscard.web.config;

import org.springframework.http.ResponseEntity;

public class UploadUserImage {
    public ResponseEntity<String> uploadUserImage(String path) {
        return ResponseEntity.ok(path);
    }
}
