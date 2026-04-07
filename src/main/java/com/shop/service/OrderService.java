package com.shop.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface OrderService {
    ModelAndView placeOrder(Integer customerId, String customerName, String deliveryAddress);
    void getOrdersForCustomer(Integer customerId, Model model);
    void getAllOrdersForAdmin(Model model);
    void updateOrderStatus(Long orderId, String status);
}
