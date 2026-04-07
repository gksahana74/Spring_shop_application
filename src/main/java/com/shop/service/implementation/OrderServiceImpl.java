package com.shop.service.implementation;

import com.shop.entity.CartItem;
import com.shop.entity.Order;
import com.shop.repository.CartItemRepository;
import com.shop.repository.OrderRepository;
import com.shop.service.CartService;
import com.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartService cartService;

    @Override
    public ModelAndView placeOrder(Integer customerId, String customerName, String deliveryAddress) {
        List<CartItem> cartItems = cartItemRepository.findByCustomerId(customerId);
        if (cartItems.isEmpty()) {
            return new ModelAndView("redirect:/customer/cart").addObject("error", "Your cart is empty!");
        }

        double total = cartItems.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setCustomerName(customerName);
        order.setDeliveryAddress(deliveryAddress);
        order.setTotalAmount(total);
        order.setStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);

        cartService.clearCart(customerId);
        return new ModelAndView("redirect:/customer/orders").addObject("success", "Order placed successfully!");
    }

    @Override
    public void getOrdersForCustomer(Integer customerId, Model model) {
        model.addAttribute("orders", orderRepository.findByCustomerId(customerId));
    }

    @Override
    public void getAllOrdersForAdmin(Model model) {
        model.addAttribute("orders", orderRepository.findAll());
    }

    @Override
    public void updateOrderStatus(Long orderId, String status) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setStatus(status);
            orderRepository.save(order);
        });
    }
}
