package com.ecom.microservice.service;


import com.ecom.microservice.api.model.NotificationEvent;
import com.ecom.microservice.api.model.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationEventPublisherService {
    private final ApplicationEventPublisher publisher;

    @Async
    public void publishEvent(NotificationEvent notification) {
        switch (notification.type()) {
            case WELCOME_MESSAGE -> {
                log.info("Publishing notification event type {}", NotificationType.WELCOME_MESSAGE);
                publisher.publishEvent(notification);
            }
            case ORDER, SHIPMENT, REVIEW -> throw new UnsupportedOperationException("Not yet implemented");
        }
    }
}
