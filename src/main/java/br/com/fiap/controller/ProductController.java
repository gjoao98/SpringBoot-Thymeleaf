package br.com.fiap.controller;

import br.com.fiap.model.Product;
import br.com.fiap.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String Products(Model model) {
        model.addAttribute("products", productService.findAll());
        return "list-products";
    }

    @GetMapping("/new")
    public String showFormNewProduct(Model model) {
        model.addAttribute("product", new Product());
        return "/register-product";
    }

    @PostMapping
    public String addProducts(@ModelAttribute @Valid Product product, BindingResult result) {
        System.out.println(product.getRegisterDate());
        if(result.hasErrors()) {
            return "/register-product";
        }
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showFormEditProduct(@PathVariable Long id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        model.addAttribute("product", product);
        return "/register-product";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}
