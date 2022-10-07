package pl.waw.great.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.waw.great.shop.controller.dto.ProductDto;
import pl.waw.great.shop.model.Product;
import pl.waw.great.shop.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product) {
        this.productRepository.create(product);
        return product;
    }

    public static Product map(ProductDto productDto) {
        return new Product(productDto.getTitle(), productDto.getDescription(), productDto.getPrice());
    }

}
