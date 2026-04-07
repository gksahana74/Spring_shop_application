package com.shop.service.implementation;

import com.shop.entity.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ModelAndView saveProduct(Product product, MultipartFile imageFile) {
        try {
            if (!imageFile.isEmpty()) {
                product.setProductImage(imageFile.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        productRepository.save(product);
        return new ModelAndView("redirect:/admin/products");
    }

    @Override
    public void getProductsForHome(Model model, String category) {
        List<Product> products = (category != null && !category.isEmpty())
                ? productRepository.findByCategory(category)
                : productRepository.findAll();

        products.forEach(p -> {
            if (p.getProductImage() != null) {
                String base64 = Base64.getEncoder().encodeToString(p.getProductImage());
                p.setProductImage(("data:image/jpeg;base64," + base64).getBytes());
            }
        });
        model.addAttribute("products", products);
        model.addAttribute("selectedCategory", category);
    }

    @Override
    public void getProductsForAdmin(Model model) {
        List<Product> products = productRepository.findAll();
        products.forEach(p -> {
            if (p.getProductImage() != null) {
                String base64 = Base64.getEncoder().encodeToString(p.getProductImage());
                p.setProductImage(("data:image/jpeg;base64," + base64).getBytes());
            }
        });
        model.addAttribute("products", products);
    }
}
