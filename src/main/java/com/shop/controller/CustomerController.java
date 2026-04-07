package com.shop.controller;

import com.shop.service.CartService;
import com.shop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    // ── Cart ──────────────────────────────────────────────
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam(defaultValue = "1") Integer quantity,
                            HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/login";
        cartService.addToCart(customerId, productId, quantity);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/login";
        cartService.getCart(customerId, model);
        model.addAttribute("customerName", session.getAttribute("customerName"));
        return "cart";
    }

    @PostMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return "redirect:/customer/cart";
    }

    // ── Orders ────────────────────────────────────────────
    @PostMapping("/checkout")
    public ModelAndView checkout(@RequestParam String deliveryAddress,
                                 HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        String customerName = (String) session.getAttribute("customerName");
        if (customerId == null) return new ModelAndView("redirect:/login");
        return orderService.placeOrder(customerId, customerName, deliveryAddress);
    }

    @GetMapping("/orders")
    public String myOrders(Model model, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/login";
        orderService.getOrdersForCustomer(customerId, model);
        return "my_orders";
    }
}
