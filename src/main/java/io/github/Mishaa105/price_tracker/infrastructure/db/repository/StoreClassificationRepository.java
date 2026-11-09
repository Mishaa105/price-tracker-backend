package io.github.Mishaa105.price_tracker.infrastructure.db.repository;

import io.github.Mishaa105.price_tracker.entity.StoreClassification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreClassificationRepository extends JpaRepository<StoreClassification, Integer>
{
    Optional<StoreClassification> findByType(String type);
}
