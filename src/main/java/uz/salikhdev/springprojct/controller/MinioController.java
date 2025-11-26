package uz.salikhdev.springprojct.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.salikhdev.springprojct.service.MinioService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class MinioController {

    private final MinioService minioService;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestPart MultipartFile file) throws Exception {
        return ResponseEntity.ok(minioService.upload(file));
    }



}