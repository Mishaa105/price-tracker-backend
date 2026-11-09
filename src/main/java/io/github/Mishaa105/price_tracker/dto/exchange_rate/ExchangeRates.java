package io.github.Mishaa105.price_tracker.dto.exchange_rate;

import java.math.BigDecimal;

public record ExchangeRates(String currency, BigDecimal exchangeRate) {}
