package io.github.Mishaa105.price_tracker.controller;

import io.github.Mishaa105.price_tracker.dto.notification.NotificationResponse;
import io.github.Mishaa105.price_tracker.service.notification.NotificationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
@SecurityRequirement(name = "bearerAuth")
public class NotificationController
{
    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationResponse> getNotifications(@AuthenticationPrincipal UUID userId)
    {
        return notificationService.getUserNotifications(userId);
    }

    @PatchMapping("/read")
    public void readNotification(@RequestParam Long notificationId, @AuthenticationPrincipal UUID userId)
    {
        notificationService.readNotification(notificationId, userId);
    }

    @DeleteMapping("/delete")
    public void deleteNotification(@RequestParam Long notificationId, @AuthenticationPrincipal UUID userId)
    {
        notificationService.deleteNotification(notificationId, userId);
    }
}
