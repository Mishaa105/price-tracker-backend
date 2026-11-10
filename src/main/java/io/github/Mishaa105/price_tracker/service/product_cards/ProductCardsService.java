package io.github.Mishaa105.price_tracker.service.product_cards;

import io.github.Mishaa105.price_tracker.dto.product_card.CurrentPriceDto;
import io.github.Mishaa105.price_tracker.dto.product_card.ProductCardDto;
import io.github.Mishaa105.price_tracker.entity.Product;
import io.github.Mishaa105.price_tracker.infrastructure.db.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductCardsService
{
    private final ProductRepository productRepository;

    public List<ProductCardDto> getProductCards(List<String> ids)
    {
        List<ProductCardDto> productCardDtoList = new ArrayList<>();

        for (String productId : ids)
        {
            Product product = productRepository.findById(productId).orElse(null);

            if (product == null)
            {
                continue;
            }

            String name = product.getName();
            String preview = product.getPreviewUrl();
            String edition = product.getEdition();
            List<String> platforms = productRepository.getProductPlatformsById(productId);
            List<CurrentPriceDto> currentPrices = productRepository.getProductCurrentPricesForMainPage(productId);

            ProductCardDto productCardDto = new ProductCardDto(name, productId, preview, edition, platforms, currentPrices);
            productCardDtoList.add(productCardDto);
        }

        log.info("Product cards successfully received");

        return productCardDtoList;
    }
}
