package io.github.Mishaa105.price_tracker.infrastructure.db.repository;

import io.github.Mishaa105.price_tracker.entity.Currency;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CurrencyRepository extends JpaRepository<Currency, Integer>
{
    @Transactional
    @Modifying
    @Query("UPDATE Currency c SET c.exchangeRate = :newRate, c.updatedAt = CURRENT_TIMESTAMP WHERE c.currency = :currencyName")
    int updateExchangeRatesByCurrency(@Param("currencyName") String currencyName, @Param("newRate") Double newRate);
}
