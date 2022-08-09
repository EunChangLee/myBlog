package com.sparta.myblog.S3Upload;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class S3Controller {
    private final S3Uploader s3Uploader;

    @PostMapping("/upload")
    public String updateUserImage(@RequestParam(value = "file") MultipartFile multipartFile) {
        String result = "";
        try {
            result = s3Uploader.uploadFiles(multipartFile, "static");
        } catch (Exception e) {
            return "업로드 에러 발생";
        }
        return result;
    }
}
