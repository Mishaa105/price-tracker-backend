package io.github.Mishaa105.price_tracker.service;

import io.github.Mishaa105.price_tracker.dto.users.UserProfileAdminResponse;
import io.github.Mishaa105.price_tracker.entity.Users;
import io.github.Mishaa105.price_tracker.infrastructure.db.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminCrudService
{
    private final UsersRepository usersRepository;

    public UserProfileAdminResponse getUserProfile(String userEmail)
    {
        Users user = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        log.info("User with email {} successfully find", userEmail);

        return new UserProfileAdminResponse(user.getUserId(), user.getEmail(), user.getName(), user.getRole());
    }

    public List<UserProfileAdminResponse> getAllUserProfiles()
    {
        List<Users> usersList = usersRepository.findAll();
        List<UserProfileAdminResponse> allUserProfiles = new ArrayList<>();

        for (Users user : usersList)
        {
            UserProfileAdminResponse userProfile = new UserProfileAdminResponse(user.getUserId(), user.getEmail(), user.getName(), user.getRole());
            allUserProfiles.add(userProfile);
        }

        return allUserProfiles;
    }
}
