package io.github.Mishaa105.price_tracker.dto.admin.stats;

import java.util.List;

public record Stats(long numberOfUsers, long numberOfProducts,
                    List<ProductCountByRegion> numberOfProductsByRegion, List<ProductCountByPlatform> numberOfProductsByPlatforms) {}
