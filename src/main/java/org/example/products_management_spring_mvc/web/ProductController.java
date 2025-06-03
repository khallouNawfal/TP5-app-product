package org.example.products_management_spring_mvc.web;

import jakarta.validation.Valid;
import org.example.products_management_spring_mvc.entities.Product;
import org.example.products_management_spring_mvc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/user/index")
    public String index(Model model, @RequestParam(name = "keyword", defaultValue = "") String k) {
        List<Product> productsList = productRepository.findByNameContainsIgnoreCase(k);
        model.addAttribute("productsList", productsList);
        model.addAttribute("keyword", k);

        return "Products";
    }

    @PostMapping("/admin/delete")
    public String delete(Long id) {
        productRepository.deleteById(id);
        return "redirect:/user/index";
    }

    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/user/index";
    }

    @GetMapping("/admin/addProduct")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/admin/save")
    public String save(@Valid Product product,
                       BindingResult bindingResult,
                       @RequestParam(name = "keyword", defaultValue = "") String Kw) {
        if (bindingResult.hasErrors()) return "addProduct";
        productRepository.save(product);
        return "redirect:/user/index?keyword=" + Kw;
    }

    @GetMapping("/admin/edit")
    public String edit(@RequestParam(name = "id", required = true) Long id,
                       Model model,
                       String Kw) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return "redirect:/user/index";
        model.addAttribute("product", product);
        model.addAttribute("keyword", Kw);
        return "editProduct";
    }
}
