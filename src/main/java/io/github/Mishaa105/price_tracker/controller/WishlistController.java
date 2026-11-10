package io.github.Mishaa105.price_tracker.controller;

import io.github.Mishaa105.price_tracker.dto.product_card.ProductCardDto;
import io.github.Mishaa105.price_tracker.service.product_cards.ProductCardsService;
import io.github.Mishaa105.price_tracker.service.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/wishlist")
public class WishlistController
{
    private final WishlistService wishlistService;
    private final ProductCardsService productCardsService;

    @PostMapping
    void addProductToWishlistByUserid(@RequestParam String productId)
    {
        UUID userId = UUID.fromString("abea82ff-2b6a-4eb3-9241-d69622c16ada");
        wishlistService.addProductToWishlist(productId, userId);
    }

    @DeleteMapping
    void deleteProductFromWishlistByUserid(@RequestParam String productId)
    {
        UUID userId = UUID.fromString("abea82ff-2b6a-4eb3-9241-d69622c16ada");
        wishlistService.deleteProductFromWishlist(productId, userId);
    }

    @GetMapping
    List<ProductCardDto> getAllProductsFromWishlistByUserid()
    {
        UUID userId = UUID.fromString("abea82ff-2b6a-4eb3-9241-d69622c16ada");
        List<String> ids = wishlistService.getAllProductsFromWishlist(userId);
        return productCardsService.getProductCards(ids);
    }
}
