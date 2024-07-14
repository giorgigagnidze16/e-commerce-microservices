package com.ecom.microservice.api.model;

/**
 * Image attachments DTO.
 *
 * @param id of image
 * @param url of image in the S3 bucket.
 */
public record ImageResponse(Long id, String url) {
}
