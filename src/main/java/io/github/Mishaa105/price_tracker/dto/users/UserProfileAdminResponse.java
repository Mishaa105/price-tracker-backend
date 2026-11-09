package io.github.Mishaa105.price_tracker.dto.users;

import io.github.Mishaa105.price_tracker.enums.auth.Role;

import java.util.UUID;

public record UserProfileAdminResponse(UUID id, String email, String name, Role role) {}
