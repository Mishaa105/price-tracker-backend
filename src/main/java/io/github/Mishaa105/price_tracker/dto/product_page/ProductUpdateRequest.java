package io.github.Mishaa105.price_tracker.dto.product_page;

import java.util.List;

public record ProductUpdateRequest(String name, String description, String preview,
                          String edition, Double averageRating, Integer ratingsCount, String storeClassification,
                          String releaseDate, String publisherName, List<String> platforms, List<String> screenLanguages,
                          List<String> spokenLanguages, List<String> genres) {}
