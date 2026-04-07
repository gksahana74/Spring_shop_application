package com.shop.service;

import org.springframework.ui.Model;

public interface CartService {
    void addToCart(Integer customerId, Long productId, Integer quantity);
    void getCart(Integer customerId, Model model);
    void removeFromCart(Long cartItemId);
    void clearCart(Integer customerId);
}
