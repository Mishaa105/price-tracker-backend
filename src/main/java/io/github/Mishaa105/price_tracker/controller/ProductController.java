package io.github.Mishaa105.price_tracker.controller;

import io.github.Mishaa105.price_tracker.dto.product_card.ProductCardDto;
import io.github.Mishaa105.price_tracker.dto.product_page.ProductPage;
import io.github.Mishaa105.price_tracker.service.product_cards.ProductCardsService;
import io.github.Mishaa105.price_tracker.service.product_page.ProductPageService;
import io.github.Mishaa105.price_tracker.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController
{
    private final SearchService searchService;
    private final ProductPageService productPageService;
    private final ProductCardsService productCardsService;

    @GetMapping("/search")
    List<ProductCardDto> search(@RequestParam String query)
    {
        List<String> ids = searchService.search(query);
        return productCardsService.getProductCards(ids);
    }

    @GetMapping("/page")
    ProductPage getProductPage(@RequestParam String productId)
    {
        return productPageService.getProductPage(productId);
    }
}
