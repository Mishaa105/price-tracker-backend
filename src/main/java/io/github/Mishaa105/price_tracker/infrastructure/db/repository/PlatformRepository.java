package io.github.Mishaa105.price_tracker.infrastructure.db.repository;

import io.github.Mishaa105.price_tracker.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface PlatformRepository extends JpaRepository<Platform, Integer>
{
    Set<Platform> findByPlatformIn(Collection<String> platforms);
}
