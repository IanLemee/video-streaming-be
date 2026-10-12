package com.tech.ian.video_streaming_be.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.Map;

@Service
public class UploadService {
    private final String bucketName;

    public UploadService(@Value("aws.s3.bucketname") String bucketName) {
        this.bucketName = bucketName;
    }

    public String presignUrl(String fileName, Map<String, String> metadata, String contentType) {
        try(S3Presigner s3Presigner = S3Presigner.create()) {
            PutObjectRequest objectRequest = PutObjectRequest
                    .builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .metadata(metadata)
                    .contentType(contentType)
                    .build();

            PutObjectPresignRequest build = PutObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(10))
                    .putObjectRequest(objectRequest)
                    .build();

            PresignedPutObjectRequest presignedPutObjectRequest = s3Presigner.presignPutObject(build);
            String url = presignedPutObjectRequest.url().toString();

            return presignedPutObjectRequest.url().toExternalForm();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
