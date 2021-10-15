package one.dio.productcatalog.controller;

import one.dio.productcatalog.model.Product;
import one.dio.productcatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    Product create(@RequestBody Product product) {
        return this.productRepository.save(product);
    }

    @GetMapping("/{id}")
    Optional<Product> getProduct(@PathVariable Integer id) {
        return this.productRepository.findById(id);
    }
}
