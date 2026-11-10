package io.github.Mishaa105.price_tracker.controller;

import io.github.Mishaa105.price_tracker.dto.users.UpdateEmailRequest;
import io.github.Mishaa105.price_tracker.dto.users.UpdateNameRequest;
import io.github.Mishaa105.price_tracker.dto.users.UpdatePasswordRequest;
import io.github.Mishaa105.price_tracker.service.UserCrudService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users/me")
public class UserController
{
    private final UserCrudService userCrudService;

    @PatchMapping("/name")
    public void updateName(@Valid @RequestBody UpdateNameRequest nameRequest)
    {
        UUID userId = UUID.fromString("abea82ff-2b6a-4eb3-9241-d69622c16ada");
        userCrudService.updateUserName(userId, nameRequest.name());
    }

    @PatchMapping("/email")
    public void updateEmail(@Valid @RequestBody UpdateEmailRequest emailRequest)
    {
        UUID userId = UUID.fromString("abea82ff-2b6a-4eb3-9241-d69622c16ada");
        userCrudService.updateUserEmail(userId, emailRequest.email());
    }

    @PatchMapping("/password")
    public void updatePassword(@Valid @RequestBody UpdatePasswordRequest passwordRequest)
    {
        UUID userId = UUID.fromString("abea82ff-2b6a-4eb3-9241-d69622c16ada");
        userCrudService.updateUserPassword(userId, passwordRequest.password());
    }
}
