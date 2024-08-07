package com.ecom.microservice.api.model;

import lombok.Builder;

/**
 * Represents notification details.
 *
 * @param from message author
 * @param to recipient
 * @param details message content
 */
@Builder
public record NotificationDetails(String from, String to, String details) {
}
