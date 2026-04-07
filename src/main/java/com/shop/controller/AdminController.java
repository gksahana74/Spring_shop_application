package com.shop.controller;

import com.shop.entity.Product;
import com.shop.repository.CustomerRepository;
import com.shop.repository.ProductRepository;
import com.shop.service.OrderService;
import com.shop.service.ProductService;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@MultipartConfig
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalProducts", productRepository.count());
        model.addAttribute("totalCustomers", customerRepository.count());
        return "admin_dashboard";
    }

    // ── Products ──────────────────────────────────────────
    @GetMapping("/products")
    public String listProducts(Model model) {
        productService.getProductsForAdmin(model);
        return "admin_products";
    }

    @GetMapping("/products/add")
    public ModelAndView showAddProduct(Model model) {
        model.addAttribute("product", new Product());
        return new ModelAndView("add_product");
    }

    @PostMapping("/products/add")
    public ModelAndView addProduct(@ModelAttribute Product product,
                                   @RequestParam("image") MultipartFile imageFile) {
        return productService.saveProduct(product, imageFile);
    }

    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/products";
    }

    // ── Customers ─────────────────────────────────────────
    @GetMapping("/customers")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "admin_customers";
    }

    @PostMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerRepository.deleteById(id);
        return "redirect:/admin/customers";
    }

    // ── Orders ────────────────────────────────────────────
    @GetMapping("/orders")
    public String listOrders(Model model) {
        orderService.getAllOrdersForAdmin(model);
        return "admin_orders";
    }

    @PostMapping("/orders/update/{id}")
    public String updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        orderService.updateOrderStatus(id, status);
        return "redirect:/admin/orders";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
