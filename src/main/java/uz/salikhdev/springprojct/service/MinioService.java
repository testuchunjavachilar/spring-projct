package uz.salikhdev.springprojct.service;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient client;

    @Value("${minio.bucket}")
    private String bucket;

    public String upload(MultipartFile file) throws Exception {

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename() + "_" + System.currentTimeMillis();

        boolean exists = client.bucketExists(
                BucketExistsArgs.builder().bucket(bucket).build()
        );

        if (!exists) {
            client.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }

        client.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(fileName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        return bucket + "/" + fileName;
    }
}