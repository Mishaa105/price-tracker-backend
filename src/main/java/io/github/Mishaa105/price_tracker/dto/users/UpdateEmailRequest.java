package io.github.Mishaa105.price_tracker.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateEmailRequest(@NotBlank @Email @Size(max = 254) String email) {}
