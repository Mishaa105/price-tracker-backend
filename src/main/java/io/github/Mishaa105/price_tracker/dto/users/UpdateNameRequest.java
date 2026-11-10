package io.github.Mishaa105.price_tracker.dto.users;

import jakarta.validation.constraints.NotBlank;

public record UpdateNameRequest(@NotBlank String name) {}
