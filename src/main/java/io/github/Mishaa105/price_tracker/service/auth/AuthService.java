package io.github.Mishaa105.price_tracker.service.auth;

import io.github.Mishaa105.price_tracker.dto.auth.LoginRequest;
import io.github.Mishaa105.price_tracker.dto.auth.LoginResponse;
import io.github.Mishaa105.price_tracker.dto.auth.RegisterRequest;
import io.github.Mishaa105.price_tracker.entity.Users;
import io.github.Mishaa105.price_tracker.enums.auth.Role;
import io.github.Mishaa105.price_tracker.infrastructure.db.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthService
{
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    private final JwtService jwtService;

    public void signUp(RegisterRequest registerRequest)
    {
        String password = registerRequest.password();
        String email = registerRequest.email();
        String username = registerRequest.username();

        if (password.getBytes(StandardCharsets.UTF_8).length > 72)
        {
            throw new IllegalArgumentException("Password must not exceed 72 bytes in UTF-8");
        }

        String passwordHash = passwordEncoder.encode(password);

        Users user = new Users(email, passwordHash, username, Role.USER);

        //TODO Собственное исключение
        if (usersRepository.existsByEmail(email))
        {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        }

        usersRepository.save(user);

        log.info("User {} successfully registered", username);
    }

    public LoginResponse signIn(LoginRequest loginRequest)
    {
        String requestEmail = loginRequest.email();
        String requestPassword = loginRequest.password();
        String jwtToken;

        Users user = usersRepository.findByEmail(requestEmail).orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        if(passwordEncoder.matches(requestPassword, user.getPassword()))
        {
            jwtToken = jwtService.generateJwt(user.getUserId().toString(), user.getRole().name());
            log.info("Jwt successfully received");
        }
        else
        {
            jwtToken = null;
            log.info("Jwt not received");
        }

        return new LoginResponse(jwtToken);
    }
}
