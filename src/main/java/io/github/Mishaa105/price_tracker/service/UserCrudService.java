package io.github.Mishaa105.price_tracker.service;

import io.github.Mishaa105.price_tracker.entity.Users;
import io.github.Mishaa105.price_tracker.infrastructure.db.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCrudService
{
    private final UsersRepository usersRepository;

    public void updateUserName(UUID userId, String name)
    {
        Users user = usersRepository.findById(userId).orElseThrow();

        user.setName(name);

        usersRepository.save(user);

        log.info("Name successfully updated to {}", name);
    }
}
