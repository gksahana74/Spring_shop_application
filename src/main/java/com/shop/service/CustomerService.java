package com.shop.service;

import com.shop.entity.Customer;
import org.springframework.web.servlet.ModelAndView;

public interface CustomerService {
    ModelAndView saveCustomer(Customer customer);
    Customer authenticate(String customerName, String password);
}
