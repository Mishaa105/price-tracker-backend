package io.github.Mishaa105.price_tracker.controller;

import io.github.Mishaa105.price_tracker.dto.exchange_rate.ExchangeRates;
import io.github.Mishaa105.price_tracker.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/currencies/")
public class CurrenciesController
{
    private final CurrencyService currencyService;

    @GetMapping("/rates")
    public List<ExchangeRates> getCurrencies()
    {
        return currencyService.getExchangeRate();
    }
}
