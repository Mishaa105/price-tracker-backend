package io.github.Mishaa105.price_tracker.infrastructure.db.repository;

import io.github.Mishaa105.price_tracker.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, Long>
{
    List<Notification> findAllByUserId_UserIdOrderBySavingTimeDesc(UUID userId);

    long deleteByIdAndUserId_UserId(Long id, UUID userId);

    Optional<Notification> findByIdAndUserId_UserId(Long id, UUID userId);
}
