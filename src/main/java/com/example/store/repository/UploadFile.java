package com.example.store.repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface UploadFile {
    File save(MultipartFile file, String folder);
}
