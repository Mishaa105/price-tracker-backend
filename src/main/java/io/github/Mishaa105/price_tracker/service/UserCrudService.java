package io.github.Mishaa105.price_tracker.service;

import io.github.Mishaa105.price_tracker.entity.Users;
import io.github.Mishaa105.price_tracker.infrastructure.db.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCrudService
{
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public void updateUserName(UUID userId, String name)
    {
        Users user = usersRepository.findById(userId).orElseThrow();

        user.setName(name);

        usersRepository.save(user);

        log.info("Name successfully updated to {}", name);
    }

    public void updateUserEmail(UUID userId, String email)
    {
        Users user = usersRepository.findById(userId).orElseThrow();

        //TODO Собственное исключение
        if (usersRepository.existsByEmail(email))
        {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        }

        user.setEmail(email);


        usersRepository.save(user);

        log.info("Email successfully updated to {}", email);
    }

    public void updateUserPassword(UUID userId, String password)
    {
        Users user = usersRepository.findById(userId).orElseThrow();

        if (password.getBytes(StandardCharsets.UTF_8).length > 72)
        {
            throw new IllegalArgumentException("Password must not exceed 72 bytes in UTF-8");
        }

        String passwordHash = passwordEncoder.encode(password);

        user.setPassword(passwordHash);

        usersRepository.save(user);

        log.info("Password successfully updated");
    }
}