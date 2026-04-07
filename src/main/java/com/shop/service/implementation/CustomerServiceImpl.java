package com.shop.service.implementation;

import com.shop.entity.Customer;
import com.shop.repository.CustomerRepository;
import com.shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ModelAndView saveCustomer(Customer customer) {
        customerRepository.save(customer);
        return new ModelAndView("redirect:/login").addObject("success", "Registration successful! Please login.");
    }

    @Override
    public Customer authenticate(String customerName, String password) {
        Optional<Customer> customer = customerRepository.findByCustomerNameAndPassword(customerName, password);
        return customer.orElse(null);
    }
}
