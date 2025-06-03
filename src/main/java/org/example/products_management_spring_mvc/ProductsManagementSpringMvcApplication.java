package org.example.products_management_spring_mvc;

import org.example.products_management_spring_mvc.entities.Product;
import org.example.products_management_spring_mvc.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication()
public class ProductsManagementSpringMvcApplication {

    private final ProductRepository productRepository;

    public ProductsManagementSpringMvcApplication(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductsManagementSpringMvcApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            productRepository.save(Product.builder()
                    .name("Computer")
                    .price(4000)
                    .quantity(55)
                    .build());
            productRepository.save(Product.builder()
                    .name("Smartphone")
                    .price(320.0)
                    .quantity(34)
                    .build());
            productRepository.save(Product.builder()
                    .name("Printer")
                    .price(50.0)
                    .quantity(30)
                    .build());
        productRepository.findAll().forEach(p ->{
            System.out.println(p.toString());
        });
        };
    }
}
