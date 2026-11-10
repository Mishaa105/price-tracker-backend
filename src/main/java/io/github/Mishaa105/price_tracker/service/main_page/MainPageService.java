package io.github.Mishaa105.price_tracker.service.main_page;

import io.github.Mishaa105.price_tracker.infrastructure.db.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainPageService
{
    private final ProductRepository productRepository;

    public List<String> getIdsForMainPage()
    {
        String yearAgoDate = Instant.now().minus(365, ChronoUnit.DAYS).toString();

        log.info("Ids for main page successfully received");
        
        return productRepository.getProductIdsForMainPage(yearAgoDate);
    }
}
