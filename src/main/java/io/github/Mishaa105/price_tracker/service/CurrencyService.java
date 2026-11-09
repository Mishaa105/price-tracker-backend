package io.github.Mishaa105.price_tracker.service;

import io.github.Mishaa105.price_tracker.dto.exchange_rate.ExchangeRates;
import io.github.Mishaa105.price_tracker.entity.Currency;
import io.github.Mishaa105.price_tracker.infrastructure.db.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyService
{
    private final CurrencyRepository currencyRepository;

    public List<ExchangeRates> getExchangeRate()
    {
        List<Currency> currencies = currencyRepository.findAll();
        List<ExchangeRates> exchangeRates = new ArrayList<>();

        for (Currency currency : currencies)
        {
            ExchangeRates exchangeRate = new ExchangeRates(currency.getCurrency(), currency.getExchangeRate());
            exchangeRates.add(exchangeRate);
        }

        log.info("Exchange rates successfully received");

        return exchangeRates;
    }
}
