package io.github.Mishaa105.price_tracker.controller;

import io.github.Mishaa105.price_tracker.dto.product_card.ProductCardDto;
import io.github.Mishaa105.price_tracker.service.main_page.MainPageService;
import io.github.Mishaa105.price_tracker.service.product_cards.ProductCardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/homepage")
public class MainPageController
{
    private final ProductCardsService productCardsService;
    private final MainPageService mainPageService;

    @GetMapping
    List<ProductCardDto> getMainPage()
    {
        List<String> ids = mainPageService.getIdsForMainPage();
        return productCardsService.getProductCards(ids);
    }
}
