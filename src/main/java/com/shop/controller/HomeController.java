package com.shop.controller;

import com.shop.entity.Customer;
import com.shop.service.CustomerService;
import com.shop.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false) String category) {
        productService.getProductsForHome(model, category);
        return "home";
    }

    @GetMapping("/signup")
    public ModelAndView showSignup(Model model) {
        model.addAttribute("customer", new Customer());
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ModelAndView doSignup(@ModelAttribute Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/login")
    public ModelAndView showLogin() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView doLogin(@RequestParam String customerName,
                                @RequestParam String password,
                                HttpSession session) {
        if (customerName.equals("admin") && password.equals("admin")) {
            return new ModelAndView("redirect:/admin/dashboard");
        }
        Customer auth = customerService.authenticate(customerName, password);
        if (auth != null) {
            session.setAttribute("customerId", auth.getCustomerId());
            session.setAttribute("customerName", auth.getCustomerName());
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("login").addObject("error", "Invalid credentials. Please try again.");
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
