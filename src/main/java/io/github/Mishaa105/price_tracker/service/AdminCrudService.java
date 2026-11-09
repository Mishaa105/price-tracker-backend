package io.github.Mishaa105.price_tracker.service;

import io.github.Mishaa105.price_tracker.dto.users.UserProfileAdminResponse;
import io.github.Mishaa105.price_tracker.entity.Users;
import io.github.Mishaa105.price_tracker.enums.auth.Role;
import io.github.Mishaa105.price_tracker.infrastructure.db.repository.ProductRepository;
import io.github.Mishaa105.price_tracker.infrastructure.db.repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
    private final ProductRepository productRepository;

    public UserProfileAdminResponse getUserProfile(String userEmail)
    {
        Users user = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        log.info("User with email {} successfully find", userEmail);

        return new UserProfileAdminResponse(user.getUserId(), user.getEmail(), user.getName(), user.getRole());
    }

    public List<UserProfileAdminResponse> getAllUserProfiles()
    {
        List<Users> users = usersRepository.findAll();
        List<UserProfileAdminResponse> allUserProfiles = new ArrayList<>();

        for (Users user : users)
        {
            UserProfileAdminResponse userProfile = new UserProfileAdminResponse(user.getUserId(), user.getEmail(), user.getName(), user.getRole());
            allUserProfiles.add(userProfile);
        }

        log.info("User list successfully received");

        return allUserProfiles;
    }

    @Transactional
    public void deleteUser(String userEmail)
    {
        int deleted = usersRepository.deleteByEmail(userEmail);

        if (deleted == 0)
        {
            throw new EntityNotFoundException("User not found");
        }

        log.info("User with email {} was successfully deleted", userEmail);
    }

    public void changeUserRole(String userEmail, Role role)
    {
        Users user = usersRepository.findByEmail(userEmail)
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        user.setRole(role);
        usersRepository.save(user);

        log.info("User with email {} successfully changed to {}", userEmail, role);
    }

    @Transactional
     public void deleteProductById(String productId)
     {
        int deleted = productRepository.deleteByProductId(productId);

         if (deleted == 0)
         {
             throw new EntityNotFoundException("Product not found");

         }

         log.info("Product with id {} was successfully deleted", productId);
     }
}
