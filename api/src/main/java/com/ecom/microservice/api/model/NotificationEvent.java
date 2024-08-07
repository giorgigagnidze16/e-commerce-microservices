package com.ecom.microservice.api.model;

import lombok.Builder;

/**
 * Notification event model.
 *
 * @param type of notification.
 * @param details notification details.
 */
@Builder
public record NotificationEvent(NotificationType type, NotificationDetails details) {
}
