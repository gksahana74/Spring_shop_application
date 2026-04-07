package com.shop.service;

import com.shop.entity.Product;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface ProductService {
    ModelAndView saveProduct(Product product, MultipartFile imageFile);
    void getProductsForHome(Model model, String category);
    void getProductsForAdmin(Model model);
}
