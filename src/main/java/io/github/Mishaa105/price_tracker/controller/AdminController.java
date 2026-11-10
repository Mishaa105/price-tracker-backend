package io.github.Mishaa105.price_tracker.controller;

import io.github.Mishaa105.price_tracker.dto.admin.ProductUpdateRequest;
import io.github.Mishaa105.price_tracker.dto.admin.stats.Stats;
import io.github.Mishaa105.price_tracker.dto.users.UserProfileAdminResponse;
import io.github.Mishaa105.price_tracker.enums.auth.Role;
import io.github.Mishaa105.price_tracker.service.AdminCrudService;
import io.github.Mishaa105.price_tracker.service.update.ExchangeRateUpdateService;
import io.github.Mishaa105.price_tracker.service.update.PlayStationDataUpdateService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/")
@SecurityRequirement(name = "bearerAuth")
public class AdminController
{
    private final PlayStationDataUpdateService playStationDataUpdateService;
    private final ExchangeRateUpdateService exchangeRateUpdateService;
    private final AdminCrudService adminCrudService;

    @PostMapping("jobs/update-prices")
    public void startDataBaseUpdate()
    {
        playStationDataUpdateService.startFullDbUpdate();
    }

    @PostMapping("jobs/update-rates")
    public void startExchangeRatesUpdate()
    {
        exchangeRateUpdateService.startExchangeRateUpdate();
    }

    @GetMapping("/users")
    public List<UserProfileAdminResponse> getUsers(@RequestParam(required = false) String email)
    {
        if (email != null)
        {
            return List.of(adminCrudService.getUserProfile(email));
        }

        return adminCrudService.getAllUserProfiles();
    }

    @DeleteMapping("/users")
    public void deleteUser(@RequestParam String email)
    {
        adminCrudService.deleteUser(email);
    }

    @PatchMapping("/users/{email}/role")
    public void changeUserRole(@PathVariable String email, @RequestParam Role role)
    {
        adminCrudService.changeUserRole(email, role);
    }

    @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable String productId)
    {
        adminCrudService.deleteProductById(productId);
    }

    @PatchMapping("/products/{productId}")
    public void updateProduct(@PathVariable String productId, @RequestBody ProductUpdateRequest request)
    {
        adminCrudService.updateProductById(productId, request);
    }

    @GetMapping("/stats")
    public Stats getStats()
    {
        return adminCrudService.getStats();
    }
}
