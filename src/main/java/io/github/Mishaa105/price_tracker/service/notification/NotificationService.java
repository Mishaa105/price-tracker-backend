package io.github.Mishaa105.price_tracker.service.notification;

import io.github.Mishaa105.price_tracker.dto.notification.NotificationResponse;
import io.github.Mishaa105.price_tracker.entity.Notification;
import io.github.Mishaa105.price_tracker.entity.Product;
import io.github.Mishaa105.price_tracker.infrastructure.db.repository.NotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class NotificationService
{
    private final NotificationRepository notificationRepository;

    public List<NotificationResponse> getUserNotifications(UUID userId)
    {
        List<Notification> notifications = notificationRepository.findAllByUserId_UserIdOrderBySavingTimeDesc(userId);
        List<NotificationResponse> notificationResponses = new ArrayList<>();

        for (Notification notification : notifications)
        {
            Product product = notification.getProductId();

            NotificationResponse notificationResponse = new NotificationResponse(notification.getId(), product.getProductId(),
                    product.getName(), product.getPreviewUrl(), notification.getOldPrice(),
                    notification.getNewPrice(), notification.getCurrency(), notification.getBrand(),
                    notification.isRead(), notification.getSavingTime());

            notificationResponses.add(notificationResponse);
        }

        log.info("Notifications successfully received");

        return notificationResponses;
    }

    @Transactional
    public void deleteNotification(Long notificationId, UUID userId)
    {
        long deleted = notificationRepository.deleteByIdAndUserId_UserId(notificationId, userId);

        if (deleted == 0)
        {
            throw new EntityNotFoundException("Notification not found");
        }

        log.info("Notification with ID {} was successfully deleted", notificationId);
    }

    public void readNotification(Long notificationId, UUID userId)
    {
        Notification notification = notificationRepository.findByIdAndUserId_UserId(notificationId, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notification not found"));;

        notification.setRead(true);
        notificationRepository.save(notification);

        log.info("Notification with id {} successfully read", notificationId);
    }
}
