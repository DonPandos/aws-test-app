package com.awstest.springboot.repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Repository
public class S3Repository {
    @Value("${s3.bucket.files.name}")
    private String bucketName;

    private final AmazonS3 s3Client;

    public S3Repository(AmazonS3 amazonS3) {
        this.s3Client = amazonS3;
    }

    public com.awstest.springboot.entity.File addFile(MultipartFile file) {
        String storageName = String.valueOf(System.currentTimeMillis());
        s3Client.putObject(bucketName, storageName, convertMultiPartFileToFile(file));
        return new com.awstest.springboot.entity.File(storageName, file.getOriginalFilename());
    }

    public String getFile(String name) {
        return s3Client.getUrl(bucketName, name).toString();
    }

    private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return file;
    }
}
