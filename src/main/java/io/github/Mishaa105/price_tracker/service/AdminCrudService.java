package io.github.Mishaa105.price_tracker.service;

import io.github.Mishaa105.price_tracker.dto.admin.ProductUpdateRequest;
import io.github.Mishaa105.price_tracker.dto.admin.stats.ProductCountByPlatform;
import io.github.Mishaa105.price_tracker.dto.admin.stats.ProductCountByRegion;
import io.github.Mishaa105.price_tracker.dto.admin.stats.Stats;
import io.github.Mishaa105.price_tracker.dto.users.UserProfileAdminResponse;
import io.github.Mishaa105.price_tracker.entity.*;
import io.github.Mishaa105.price_tracker.enums.auth.Role;
import io.github.Mishaa105.price_tracker.infrastructure.db.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminCrudService
{
    private final UsersRepository usersRepository;
    private final ProductRepository productRepository;
    private final StoreClassificationRepository storeClassificationRepository;
    private final PublisherRepository publisherRepository;
    private final PlatformRepository platformRepository;
    private final LanguageRepository languageRepository;
    private final GenreRepository genreRepository;

    public UserProfileAdminResponse getUserProfile(String userEmail)
    {
        Users user = usersRepository.findByEmail(userEmail).orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

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
        Users user = usersRepository.findByEmail(userEmail).orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

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

    @Transactional
    public void updateProductById(String productId, ProductUpdateRequest updateRequest)
    {
        Product product = productRepository.findByProductId(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        if (updateRequest.name() != null)
        {
            product.setName(updateRequest.name());
        }

        if (updateRequest.description() != null)
        {
            product.setDescription(updateRequest.description());
        }

        if (updateRequest.preview() != null)
        {
            product.setPreviewUrl(updateRequest.preview());
        }

        if (updateRequest.edition() != null)
        {
            product.setEdition(updateRequest.edition());
        }

        if (updateRequest.averageRating() != null)
        {
            product.setAverageRating(updateRequest.averageRating());
        }

        if (updateRequest.ratingsCount() != null)
        {
            product.setRatingsCount(updateRequest.ratingsCount());
        }

        if (updateRequest.releaseDate() != null)
        {
            product.setReleaseDate(updateRequest.releaseDate());
        }

        if (updateRequest.storeClassification() != null)
        {
            StoreClassification storeClassification = storeClassificationRepository.findByType(updateRequest.storeClassification()).orElseThrow();

            product.setStoreClassification(storeClassification);
        }

        if (updateRequest.publisherName() != null)
        {
            Publisher publisher = publisherRepository.findByName(updateRequest.publisherName()).orElseThrow();

            product.setPublisherName(publisher);
        }

        if (updateRequest.platforms() != null)
        {
            Set<Platform> platforms = platformRepository.findByPlatformIn((updateRequest.platforms()));

            if (platforms.size() != new HashSet<>(updateRequest.platforms()).size())
            {
                throw new IllegalArgumentException("Some platforms were not found");
            }

            product.setPlatforms(platforms);
        }

        if (updateRequest.spokenLanguages() != null || updateRequest.screenLanguages() != null)
        {
            Set<Language> languages = new HashSet<>(product.getLanguages());

            if (updateRequest.screenLanguages() != null)
            {
                Set<Language> screenLanguages = languageRepository.findByLanguageInAndType(updateRequest.screenLanguages(), "SCREEN");

                if (screenLanguages.size() != new HashSet<>(updateRequest.screenLanguages()).size())
                {
                    throw new IllegalArgumentException("Some screen languages were not found");
                }

                languages.removeIf(language -> "SCREEN".equals(language.getType()));
                languages.addAll(screenLanguages);
            }

            if (updateRequest.spokenLanguages() != null)
            {
                Set<Language> spokenLanguages = languageRepository.findByLanguageInAndType(updateRequest.spokenLanguages(), "SPOKEN");

                if (spokenLanguages.size() != new HashSet<>(updateRequest.spokenLanguages()).size())
                {
                    throw new IllegalArgumentException("Some spoken languages were not found");
                }

                languages.removeIf(language -> "SPOKEN".equals(language.getType()));
                languages.addAll(spokenLanguages);
            }

            product.setLanguages(languages);
        }

        if (updateRequest.genres() != null)
        {
            Set<Genre> genres = genreRepository.findByGenreIn((updateRequest.genres()));

            if (genres.size() != new HashSet<>(updateRequest.genres()).size())
            {
                throw new IllegalArgumentException("Some genres were not found");
            }

            product.setGenres(genres);
        }

        productRepository.save(product);
    }

    public Stats getStats()
    {
        long numberOfUsers = usersRepository.count();
        long numberOfProducts = productRepository.count();

        List<ProductCountByRegion> productCountByRegion = productRepository.countProductsByRegion();
        List<ProductCountByPlatform> productCountByPlatform = productRepository.countProductsByPlatform();

        return new Stats(numberOfUsers, numberOfProducts, productCountByRegion, productCountByPlatform);
    }
}
