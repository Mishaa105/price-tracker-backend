package io.github.Mishaa105.price_tracker.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordRequest(@NotBlank @Size(min = 8, max = 100) String password) {}
