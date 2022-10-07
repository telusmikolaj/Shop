package pl.waw.great.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.waw.great.shop.controller.dto.ProductDto;
import pl.waw.great.shop.model.Product;
import pl.waw.great.shop.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product create(ProductDto productDto) {
        Product product = productService.create(ProductService.map(productDto));
        return product;
    }

}
