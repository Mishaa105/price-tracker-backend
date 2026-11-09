package io.github.Mishaa105.price_tracker.controller;

import io.github.Mishaa105.price_tracker.dto.users.UserProfileAdminResponse;
import io.github.Mishaa105.price_tracker.service.AdminCrudService;
import io.github.Mishaa105.price_tracker.service.update.ExchangeRateUpdateService;
import io.github.Mishaa105.price_tracker.service.update.PlayStationDataUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admin/")
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
    public UserProfileAdminResponse getUserProfile(@RequestParam String email)
    {
        return adminCrudService.getUserProfile(email);
    }
}
