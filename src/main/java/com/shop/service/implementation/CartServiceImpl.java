package com.shop.service.implementation;

import com.shop.entity.CartItem;
import com.shop.entity.Product;
import com.shop.repository.CartItemRepository;
import com.shop.repository.ProductRepository;
import com.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addToCart(Integer customerId, Long productId, Integer quantity) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null || product.getStock() < quantity) return;

        CartItem item = new CartItem();
        item.setCustomerId(customerId);
        item.setProductId(productId);
        item.setProductName(product.getProductName());
        item.setPrice(product.getPrice());
        item.setQuantity(quantity);
        cartItemRepository.save(item);
    }

    @Override
    public void getCart(Integer customerId, Model model) {
        List<CartItem> items = cartItemRepository.findByCustomerId(customerId);
        double total = items.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
        model.addAttribute("cartItems", items);
        model.addAttribute("cartTotal", total);
    }

    @Override
    public void removeFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void clearCart(Integer customerId) {
        cartItemRepository.deleteByCustomerId(customerId);
    }
}
