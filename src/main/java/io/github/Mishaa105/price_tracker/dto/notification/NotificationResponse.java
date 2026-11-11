package io.github.Mishaa105.price_tracker.dto.notification;

import java.time.LocalDateTime;

public record NotificationResponse(Long id, String productId, String productName, String preview, Integer oldPrice, Integer newPrice,
                                   String currency, String brand, boolean isRead, LocalDateTime createdAt) {}

