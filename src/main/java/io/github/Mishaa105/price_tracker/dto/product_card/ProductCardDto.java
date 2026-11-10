package io.github.Mishaa105.price_tracker.dto.product_card;

import java.util.List;

public record ProductCardDto(String name, String productId, String preview, String edition, List<String> platforms, List<CurrentPriceDto> currentPrices) {}
