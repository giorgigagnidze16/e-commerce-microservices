package com.ecom.microservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Image service.
 */
@Service
@RequiredArgsConstructor
public class ImageService {
    private final Minio minio;

    /**
     * Uploads images to S3 bucket and gets resource url.
     *
     * @param image to be uploaded.
     * @return String publicly accessible object url.
     * @throws Exception if upload fails
     */
    String uploadAndGetUrl(MultipartFile image) throws Exception {
        return minio.save(image.getResource());
    }
}
