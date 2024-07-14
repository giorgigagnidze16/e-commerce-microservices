package com.ecom.microservice.service;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class Minio {
    private final MinioClient client;
    private final String bucket;
    private final String url;

    Minio(
        @Value("${app.minio.url}") String url,
        @Value("${app.minio.bucket}") String bucket,
        @Value("${app.minio.username}") String username,
        @Value("${app.minio.password}") String password
    ) {
        this.client = MinioClient.builder()
            .endpoint(url)
            .credentials(username, password)
            .build();
        this.bucket = bucket;
        this.url = url + bucket + "/";
    }

    String save(Resource resource)
        throws IOException, ServerException, InsufficientDataException, ErrorResponseException,
        NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        var args = PutObjectArgs.builder()
            .bucket(bucket)
            .object(resource.getFilename())
            .stream(resource.getInputStream(), -1, 6 * 1024 * 1024)
            .build();
        client.putObject(args);
        log.debug("Resource {} successfully uploaded to bucket '{}'", resource.getFilename(), bucket);
        return url + resource.getFilename();
    }
}
