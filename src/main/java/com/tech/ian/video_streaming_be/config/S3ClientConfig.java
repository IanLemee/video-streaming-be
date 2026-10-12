package com.tech.ian.video_streaming_be.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configurable
public class S3ClientConfig {
    private static final String REGION = "";

    @Bean
    public S3Client s3Client() {
        return S3Client
                .builder()
                .region(Region.of(REGION))
                .build();
    }

}
