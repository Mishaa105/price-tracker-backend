package io.github.Mishaa105.price_tracker.controller;

import io.github.Mishaa105.price_tracker.dto.product_card.ProductCardDto;
import io.github.Mishaa105.price_tracker.service.product_cards.ProductCardsService;
import io.github.Mishaa105.price_tracker.service.wishlist.WishlistService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/wishlist")
@SecurityRequirement(name = "bearerAuth")
public class WishlistController
{
    private final WishlistService wishlistService;
    private final ProductCardsService productCardsService;

    @PostMapping
    void addProductToWishlistByUserid(@RequestParam String productId, @AuthenticationPrincipal UUID userId)
    {
        wishlistService.addProductToWishlist(productId, userId);
    }

    @DeleteMapping
    void deleteProductFromWishlistByUserid(@RequestParam String productId, @AuthenticationPrincipal UUID userId)
    {
        wishlistService.deleteProductFromWishlist(productId, userId);
    }

    @GetMapping
    List<ProductCardDto> getAllProductsFromWishlistByUserid(@AuthenticationPrincipal UUID userId)
    {
        List<String> ids = wishlistService.getAllProductsFromWishlist(userId);
        return productCardsService.getProductCards(ids);
    }
}
