package io.github.Mishaa105.price_tracker.controller;

import io.github.Mishaa105.price_tracker.dto.users.UpdateEmailRequest;
import io.github.Mishaa105.price_tracker.dto.users.UpdateNameRequest;
import io.github.Mishaa105.price_tracker.dto.users.UpdatePasswordRequest;
import io.github.Mishaa105.price_tracker.dto.users.UserProfileResponse;
import io.github.Mishaa105.price_tracker.service.UserCrudService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users/me")
@SecurityRequirement(name = "bearerAuth")
public class UserController
{
    private final UserCrudService userCrudService;

    @PatchMapping("/name")
    public void updateName(@Valid @RequestBody UpdateNameRequest nameRequest, @AuthenticationPrincipal UUID userId)
    {
        userCrudService.updateUserName(userId, nameRequest.name());
    }

    @PatchMapping("/email")
    public void updateEmail(@Valid @RequestBody UpdateEmailRequest emailRequest, @AuthenticationPrincipal UUID userId)
    {
        userCrudService.updateUserEmail(userId, emailRequest.email());
    }

    @PatchMapping("/password")
    public void updatePassword(@Valid @RequestBody UpdatePasswordRequest passwordRequest, @AuthenticationPrincipal UUID userId)
    {
        userCrudService.updateUserPassword(userId, passwordRequest.password());
    }

    @GetMapping
    public UserProfileResponse getProfile(@AuthenticationPrincipal UUID userId)
    {
        return userCrudService.getUserProfile(userId);
    }
}
