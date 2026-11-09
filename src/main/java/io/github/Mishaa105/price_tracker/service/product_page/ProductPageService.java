package io.github.Mishaa105.price_tracker.service.product_page;

import io.github.Mishaa105.price_tracker.dto.product_page.CurrentPricePoint;
import io.github.Mishaa105.price_tracker.dto.product_page.PricePoint;
import io.github.Mishaa105.price_tracker.dto.product_page.ProductPage;
import io.github.Mishaa105.price_tracker.infrastructure.db.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductPageService
{
    private final ProductRepository productRepository;

    public ProductPage getProductPage(String productId)
    {
        String productName = productRepository.getProductNameById(productId);
        String description = productRepository.getProductDescriptionById(productId);
        String previewUrl = productRepository.getProductPreviewById(productId);
        String edition = productRepository.getProductEditionById(productId);
        Double averageRating = productRepository.getProductAverageRatingById(productId);
        Integer ratingsCount = productRepository.getProductRatingsCountById(productId);
        String releaseDate = productRepository.getProductReleaseDateById(productId);
        String storeClassification = productRepository.getProductStoreClassificationById(productId);
        String publisherName = productRepository.getProductPublisherNameById(productId);
        List<String> platforms = productRepository.getProductPlatformsById(productId);
        List<String> genres = productRepository.getProductGenresById(productId);
        List<String> screenLanguages = productRepository.getProductScreenLanguagesById(productId);
        List<String> spokenLanguages = productRepository.getProductSpokenLanguagesById(productId);
        List<PricePoint> allPrices = productRepository.getProductAllPrices(productId);
        List<CurrentPricePoint> currentPrices = productRepository.getProductCurrentPrices(productId);

        return new ProductPage(productName, description, previewUrl, edition, averageRating,
                ratingsCount, storeClassification, releaseDate, publisherName, platforms,
                screenLanguages, spokenLanguages, genres, allPrices, currentPrices);
    }
}
