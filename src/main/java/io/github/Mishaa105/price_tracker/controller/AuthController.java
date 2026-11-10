package io.github.Mishaa105.price_tracker.controller;

import io.github.Mishaa105.price_tracker.dto.auth.LoginRequest;
import io.github.Mishaa105.price_tracker.dto.auth.LoginResponse;
import io.github.Mishaa105.price_tracker.dto.auth.RegisterRequest;
import io.github.Mishaa105.price_tracker.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController
{
    private final AuthService authService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest registerRequest)
    {
        authService.signUp(registerRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest)
    {
        return authService.signIn(loginRequest);
    }
}
