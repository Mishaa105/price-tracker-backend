package io.github.Mishaa105.price_tracker.infrastructure.db.repository;

import io.github.Mishaa105.price_tracker.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, Integer>
{
    Set<Genre> findByGenreIn(Collection<String> genres);
}
