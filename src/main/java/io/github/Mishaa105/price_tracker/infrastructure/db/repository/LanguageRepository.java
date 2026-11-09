package io.github.Mishaa105.price_tracker.infrastructure.db.repository;

import io.github.Mishaa105.price_tracker.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Set;

public interface LanguageRepository extends JpaRepository<Language, Integer>
{
    Set<Language> findByLanguageInAndType(List<String> languages, String type);
}
