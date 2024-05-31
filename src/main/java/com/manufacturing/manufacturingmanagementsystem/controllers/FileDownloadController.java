package com.manufacturing.manufacturingmanagementsystem.controllers;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
// Author: Pham Van Cao
// this class is used to handle the file download
@RestController
@RequestMapping("/api/file")
public class FileDownloadController {

    // Author: Pham Van Cao
    // this method is used to download the file
    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile() throws IOException {
        // Replace with the actual file path
        String filePath = "C:\\Users\\Dell\\Downloads\\hello.txt";
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found at " + filePath);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
